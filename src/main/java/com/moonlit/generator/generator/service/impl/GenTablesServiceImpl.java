package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.utils.DatabaseUtils;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.GenTables;
import com.moonlit.generator.generator.entity.dto.GenTablesDTO;
import com.moonlit.generator.generator.entity.dto.SaveGenTablesDTO;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import com.moonlit.generator.generator.mapper.GenAuthorConfigMapper;
import com.moonlit.generator.generator.mapper.GenDatabaseMapper;
import com.moonlit.generator.generator.mapper.GenTablesMapper;
import com.moonlit.generator.generator.service.GenTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private GenAuthorConfigMapper genAuthorConfigMapper;

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
     * 新增
     *
     * @param genTables 表实体
     * @return 结果
     */
    @Override
    public Boolean insertTables(SaveGenTablesDTO genTables) {
        //
        Long databaseId = genTables.getDatabaseId();

        for (String tableName : genTables.getTableNames()) {

        }



        GenTables tables = new GenTables();
        return this.save(tables);
    }

    /**
     * 修改
     *
     * @param genTables 表实体
     * @return 结果
     */
    @Override
    public Boolean updateTables(GenTables genTables) {
        genTables.setUpdateDate(LocalDateTime.now());
        return this.updateById(genTables);
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
     * 生成表
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
            throw new BusinessException(DatabaseErrorCode.UNABLE_TO_CONNECT);
        }
        // 獲取庫内所有的表
        ArrayList<DatabaseTablesVO> list = DatabaseUtils.getTablesDetails(genDatabase, genAuthorConfigMapper.getConfigByType().getPublicKey());
        List<String> tableNames = this.baseMapper.selectTableNames(databaseId);
        // 移除已存在的表
        if (tableNames.size() > 0) {
            for (DatabaseTablesVO databaseTablesVO : list) {
                for (String tableName : tableNames) {
                    if (databaseTablesVO.getTableName().equals(tableName)) {
                        list.remove(databaseTablesVO);
                    }
                }
            }
        }
        return list;
    }

}
