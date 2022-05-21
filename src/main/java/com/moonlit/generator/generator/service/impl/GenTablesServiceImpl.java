package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.utils.MySqlUtils;
import com.moonlit.generator.common.utils.NamingStrategy;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.GenTables;
import com.moonlit.generator.generator.entity.GenTablesConfig;
import com.moonlit.generator.generator.entity.dto.GenTablesDTO;
import com.moonlit.generator.generator.entity.dto.SaveGenTablesDTO;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import com.moonlit.generator.generator.mapper.GenDatabaseMapper;
import com.moonlit.generator.generator.mapper.GenSystemConfigMapper;
import com.moonlit.generator.generator.mapper.GenTablesConfigMapper;
import com.moonlit.generator.generator.mapper.GenTablesMapper;
import com.moonlit.generator.generator.service.GenTablesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 數據表配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:21
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTablesServiceImpl extends ServiceImpl<GenTablesMapper, GenTables> implements GenTablesService {

    @Autowired
    private GenDatabaseMapper genDatabaseMapper;

    @Autowired
    private GenSystemConfigMapper genSystemConfigMapper;

    @Autowired
    private GenTablesConfigMapper genTablesConfigMapper;

    /**
     * 条件分页查询
     *
     * @param genTablesDTO 查询实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTables> pageList(GenTablesDTO genTablesDTO) {
        LambdaQueryWrapper<GenTables> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(genTablesDTO)) {
            if (ObjectUtil.isNotEmpty(genTablesDTO.getDatabaseId())) {
                queryWrapper.eq(GenTables::getDatabaseId, genTablesDTO.getDatabaseId());
            }
            if (ObjectUtil.isNotEmpty(genTablesDTO.getTableName())) {
                queryWrapper.eq(GenTables::getTableName, genTablesDTO.getTableName());
            }
            if (ObjectUtil.isNotEmpty(genTablesDTO.getTableComment())) {
                queryWrapper.eq(GenTables::getTableComment, genTablesDTO.getTableComment());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 获取业务名
     * TODO 設計不合理
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf(CharacterConstant.UNDER_LINE);
        return StringUtils.substring(tableName, lastIndex + 1, tableName.length());
    }

    /**
     * 獲取業務描述
     *
     * @param tableComment 表描述
     * @return 業務描述
     */
    public static String getBusinessComment(String tableComment) {
        int index = tableComment.length() - 1;
        return tableComment.substring(index).contains("表") ? tableComment.substring(0, index) : tableComment;
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteTablesByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 新增
     *
     * @param genTablesDTO 表实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public Boolean insertTables(SaveGenTablesDTO genTablesDTO) {
        for (DatabaseTablesVO tablesVO : genTablesDTO.getList()) {
            GenTables tables = null;
            try {
                tables = initializeTable(genTablesDTO.getDatabaseId(), tablesVO.getTableName(), tablesVO.getTableComment(), genTablesDTO.getTableConfigId());
                baseMapper.insert(tables);
                // 插入數據後的主鍵
                Integer row = tables.getId();
                if (row > 0) {
                    //  TODO 插入表字段信息


                }
            } catch (Exception e) {
                throw new BusinessException(DatabaseErrorCode.SAVE_ERROR);
            }
        }
        // TODO 添加表字段
        return true;
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 修改
     *
     * @param genTables 表实体
     * @return 结果
     */
    @Override
    public Boolean updateTables(GenTables genTables) {
        GenTables tables = this.getById(genTables.getId());
        // 表配置變動
        if (!genTables.getConfigId().equals(tables.getConfigId())) {
            genTables.setClassName(convertClassName(genTables.getTableName(), genTables.getConfigId()));
        }
        genTables.setUpdateDate(LocalDateTime.now());
        return this.updateById(genTables);
    }

    /**
     * 獲取未添加的表
     *
     * @param databaseId 主键
     * @return 结果
     */
    @Override
    public List<DatabaseTablesVO> list(Long databaseId) {
        // 檢查庫是否存在
        LambdaQueryWrapper<GenDatabase> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenDatabase::getId, databaseId);
        GenDatabase genDatabase = genDatabaseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(genDatabase)) {
            throw new BusinessException(DatabaseErrorCode.DATABASE_NOT_EXIST);
        }

        // 獲取AES密鑰
        GenSystemConfig systemConfig = genSystemConfigMapper.selectById(1);
        String key = RsaUtils.publicDecrypt(systemConfig.getSalt(), systemConfig.getPublicKey());
        // 獲取庫内所有的表
        ArrayList<DatabaseTablesVO> list = MySqlUtils.getTablesDetails(genDatabase, key);
        List<String> tableNames = this.baseMapper.selectTableNames(databaseId);
        // 移除已存在的表
        if (tableNames.size() > 0) {
            list.removeIf(databaseTablesVO -> tableNames.contains(databaseTablesVO.getTableName()));
        }
        return list;
    }

    /**
     * 初始化表實體
     *
     * @param databaseId    數據庫id
     * @param tableName     表名
     * @param tableComment  表注釋
     * @param tableConfigId 表配置id
     * @return 表實體
     */
    private GenTables initializeTable(Long databaseId, String tableName, String tableComment, Long tableConfigId) {
        GenTables genTables = new GenTables(databaseId, tableName, tableComment, tableConfigId);
        genTables.setClassName(convertClassName(tableName, tableConfigId));
        genTables.setBusinessName(getBusinessName(tableName));
        genTables.setFunctionName(tableName);
        return genTables;
    }

    /**
     * 表名轉類名
     *
     * @param tableName     表名稱
     * @param tableConfigId 表配置id
     * @return 類名稱
     */
    private String convertClassName(String tableName, Long tableConfigId) {
        GenTablesConfig tablesConfig = genTablesConfigMapper.selectById(tableConfigId);
        if (tablesConfig.getRemovePrefix()) {
            return NamingStrategy.firstToUpperCase(NamingStrategy.removePrefixAndCamel(tableName, tablesConfig.getTablePrefix()));
        }
        return NamingStrategy.firstToUpperCase(NamingStrategy.underlineToCamel(tableName));
    }
}
