package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.utils.MySqlUtils;
import com.moonlit.generator.common.utils.NamingStrategyUtils;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.constants.error.TableConfigErrorCode;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.moonlit.generator.generator.entity.dto.GenTableDTO;
import com.moonlit.generator.generator.entity.dto.SaveGenTableDTO;
import com.moonlit.generator.generator.entity.dto.SaveTableColumnDTO;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import com.moonlit.generator.generator.mapper.GenTableMapper;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import com.moonlit.generator.generator.service.GenTableColumnService;
import com.moonlit.generator.generator.service.GenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    @Autowired
    private GenDatabaseService databaseService;

    @Autowired
    private GenSystemConfigService systemConfigService;

    @Autowired
    private GenTableColumnService tableColumnService;

    /**
     * 条件分页查询
     *
     * @param genTableDTO 查询实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTable> pageList(GenTableDTO genTableDTO) {
        LambdaQueryWrapper<GenTable> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(genTableDTO)) {
            if (ObjectUtil.isNotEmpty(genTableDTO.getDatabaseId())) {
                queryWrapper.eq(GenTable::getDatabaseId, genTableDTO.getDatabaseId());
            }
            if (ObjectUtil.isNotEmpty(genTableDTO.getTableName())) {
                queryWrapper.like(GenTable::getTableName, genTableDTO.getTableName());
            }
            if (ObjectUtil.isNotEmpty(genTableDTO.getTableComment())) {
                queryWrapper.like(GenTable::getTableComment, genTableDTO.getTableComment());
            }
        }
        queryWrapper.orderByDesc(GenTable::getCreateDate);
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param saveDTO 表实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public Boolean insertTable(SaveGenTableDTO saveDTO) {
        for (DatabaseTablesVO tablesVO : saveDTO.getList()) {
            try {
                GenTable tables = initializeTable(saveDTO.getDatabaseId(), tablesVO.getTableName(), tablesVO.getTableComment());
                baseMapper.insert(tables);
                // 插入數據後的主鍵
                Long row = tables.getId();
                if (row > 0) {
                    // 插入表字段信息
                    SaveTableColumnDTO columnDTO = new SaveTableColumnDTO(saveDTO.getDatabaseId(), tablesVO.getTableName(), row);
                    tableColumnService.insertTableColumn(columnDTO);
                }
            } catch (Exception e) {
                throw new BusinessException(DatabaseErrorCode.SAVE_ERROR);
            }
        }
        return true;
    }

    /**
     * 修改
     *
     * @param genTable 表实体
     * @return 结果
     */
    @Override
    public Boolean updateTable(GenTable genTable) {
        genTable.setUpdateDate(LocalDateTime.now());
        return this.updateById(genTable);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteTableByIds(String ids) {
        Collection<String> list = Arrays.asList(Convert.toStrArray(ids));
        // 刪除子表的數據
        tableColumnService.removeByIds(tableColumnService.listColumnsByTableId(list));
        return this.removeByIds(list);
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
        GenDatabase genDatabase = databaseService.getById(databaseId);
        if (ObjectUtil.isEmpty(genDatabase)) {
            throw new BusinessException(DatabaseErrorCode.DATABASE_NOT_EXIST);
        }

        // 獲取AES密鑰
        String rsaKey = systemConfigService.getRsaKey();
        // 獲取庫内所有的表
        ArrayList<DatabaseTablesVO> list = MySqlUtils.getTablesDetails(genDatabase, rsaKey);
        List<String> tableNames = baseMapper.selectTableNames(databaseId);
        // 移除已存在的表
        if (tableNames.size() > 0) {
            list.removeIf(databaseTablesVO -> tableNames.contains(databaseTablesVO.getTableName()));
        }
        return list;
    }

    /**
     * 獲取模板所需要的數據
     *
     * @param tableId       表id
     * @param tableConfigId 表配置id
     * @return 數據
     */
    @Override
    public TableConfigAndDataAndColumnsBO getTableData(Long tableId, Long tableConfigId) {
        TableConfigAndDataAndColumnsBO tableData = baseMapper.getTableData(tableId, tableConfigId);
        tableData.setObjectName(StringUtils.firstToLowerCase(tableData.getObjectName()));

        // 查詢不到作者配置
        if (ObjectUtil.isEmpty(tableData.getTableConfigId())) {
            throw new BusinessException(TableConfigErrorCode.DEFAULT_AUTHOR_CONFIGURATION_NOT_FOUND);
        }

        tableData.setTableColumns(tableColumnService.getTableColumnByTableId(tableId));
        return tableData;
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 初始化表實體
     *
     * @param databaseId   數據庫id
     * @param tableName    表名
     * @param tableComment 表注釋
     * @return 表實體
     */
    private GenTable initializeTable(Long databaseId, String tableName, String tableComment) {
        GenTable genTable = new GenTable(databaseId, tableName, tableComment);
        genTable.setObjectName(StringUtils.underlineToCamel(tableName));
        genTable.setBusinessName(NamingStrategyUtils.getBusinessName(tableName));
        genTable.setFunctionName(NamingStrategyUtils.getBusinessComment(tableComment));
        return genTable;
    }

}
