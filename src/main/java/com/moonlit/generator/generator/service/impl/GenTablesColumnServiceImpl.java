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
import com.moonlit.generator.common.utils.NamingStrategy;
import com.moonlit.generator.generator.constants.DatabaseConstants;
import com.moonlit.generator.generator.constants.WebConstants;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTablesColumnDTO;
import com.moonlit.generator.generator.entity.vo.TableFieldVO;
import com.moonlit.generator.generator.mapper.GenTablesColumnMapper;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import com.moonlit.generator.generator.service.GenTablesColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 數據表字段詳情业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:03
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTablesColumnServiceImpl extends ServiceImpl<GenTablesColumnMapper, GenTablesColumn> implements GenTablesColumnService {

    @Autowired
    private GenDatabaseService databaseService;

    @Autowired
    private GenSystemConfigService configService;

    /**
     * 条件分页查询
     *
     * @param dto 查询实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTablesColumn> pageList(GenColumnDTO dto) {
        LambdaQueryWrapper<GenTablesColumn> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenTablesColumn::getTableId, dto.getTableId());
        if (ObjectUtil.isNotNull(dto)) {
            if (ObjectUtil.isNotEmpty(dto.getColumnName())) {
                queryWrapper.like(GenTablesColumn::getColumnName, dto.getColumnName());
            }
            if (ObjectUtil.isNotEmpty(dto.getColumnComment())) {
                queryWrapper.like(GenTablesColumn::getColumnComment, dto.getColumnComment());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增/刷新
     *
     * @param saveDTO 实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public Boolean insertTablesColumn(SaveTablesColumnDTO saveDTO) {
        try {
            GenDatabase database = databaseService.getById(saveDTO.getDatabaseId());
            ArrayList<TableFieldVO> vos = MySqlUtils.getFieldDetails(database, configService.getRsaKey(), saveDTO.getTableName());
            // 移除以添加過的列
            removeExistingColumns(saveDTO.getTableId(), vos);
            if (vos.size() > 0) {
                // 初始化數據
                ArrayList<GenTablesColumn> list = initializeColumns(saveDTO.getTableId(), vos);
                return this.saveBatch(list);
            }
        } catch (Exception e) {
            throw new BusinessException(DatabaseErrorCode.SAVE_ERROR);
        }
        return true;
    }

    /**
     * 修改
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    @Override
    public Boolean updateTablesColumn(GenTablesColumn genTablesColumn) {
        genTablesColumn.setUpdateDate(LocalDateTime.now());
        return this.updateById(genTablesColumn);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteTablesColumnByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 移除以存在的列數據
     *
     * @param tableId 表id
     * @param vos     所有列
     */
    private void removeExistingColumns(Long tableId, ArrayList<TableFieldVO> vos) {
        Collection<String> tablesColumns = baseMapper.selectTablesName(tableId);
        if (tablesColumns.size() > 0) {
            vos.removeIf(vo -> tablesColumns.contains(vo.getColumnName()));
        }
    }

    /**
     * 初始化表字段實體
     *
     * @param tableId 表id
     * @param vos     字段信息
     * @return 篩選後的數據
     */
    private ArrayList<GenTablesColumn> initializeColumns(Long tableId, ArrayList<TableFieldVO> vos) {
        ArrayList<GenTablesColumn> list = new ArrayList<>();

        for (TableFieldVO vo : vos) {
            // 列名
            String columnName = vo.getColumnName();

            // 填充字段
            GenTablesColumn column = new GenTablesColumn(tableId, vo);
            column.setJavaField(StringUtils.underlineToCamel(columnName));
            // 移除末尾的參數
            String columnType = NamingStrategy.substringBefore(vo.getColumnType(), "(");

            // 設置java類型
            if (isContains(DatabaseConstants.DATABASE_STRING_TYPE, columnType)) {
                // 文本類型
                column.setJavaType(DatabaseConstants.STRING_TYPE);
            } else if (isContains(DatabaseConstants.DATABASE_DATE_TYPE, columnType)) {
                // 時間類型
                column.setJavaType(DatabaseConstants.LOCAL_DATE_TIME_TYPE);
                column.setHtmlType(WebConstants.WEB_DATETIME);
            } else if (isContains(DatabaseConstants.DATABASE_NUMBER_TYPE, columnType)) {
                // 數字類型
                String type = DatabaseConstants.INTEGER_TYPE;
                if (isContains(DatabaseConstants.FLOATING_POINT, columnType)) {
                    // 浮點型
                    type = DatabaseConstants.BIG_DECIMAL_TYPE;
                } else if ("bigint".equals(columnType) || StringUtils.endsWith(columnName, "id")) {
                    // 主鍵或者包含id的字段
                    type = DatabaseConstants.LONG_TYPE;
                }
                column.setJavaType(type);
            }

            // 主鍵
            Boolean columnKey = vo.getColumnKey();
            // 新增字段
            if (!columnKey || isContains(DatabaseConstants.DATABASE_DATE_TYPE, columnType)) {
                column.setIsInsert(false);
            }
            // 編輯字段
            if (!columnKey && isContains(DatabaseConstants.DATABASE_STRING_TYPE, columnType)) {
                column.setIsEdit(false);
            }
            // 列表字段
            if (!columnKey && isContains(DatabaseConstants.DATABASE_NUMBER_TYPE, columnType)) {
                column.setIsList(false);
            }
            // 查詢字段
            if (!columnKey && !isContains(DatabaseConstants.COLUMN_TYPE_NOT_QUERY, columnType) && !isContains(DatabaseConstants.COLUMN_NOT_QUERY, columnName)) {
                column.setIsQuery(false);
            }
            // 查詢類型
            if (StringUtils.endsWith(columnName, "name")) {
                column.setQueryType("like");
            }
            // 前端標簽
            if (ObjectUtil.isNull(column.getHtmlType())) {
                if (StringUtils.endsWith(columnName, "status")) {
                    // 單選框
                    column.setHtmlType(WebConstants.WEB_RADIO);
                } else if (StringUtils.endsWith(columnName, "type") || StringUtils.endsWith(columnName, "sex")) {
                    // 下拉框
                    column.setHtmlType(WebConstants.WEB_SELECT);
                } else if (isContains(DatabaseConstants.TEXT_TYPE, columnType)) {
                    column.setHtmlType(WebConstants.WEB_TEXT);
                }
            }
            list.add(column);
        }
        return list;
    }

    /**
     * 數組是否包含關鍵字
     *
     * @param arr        數組
     * @param columnType 字段類型
     * @return 結果
     */
    private static Boolean isContains(String[] arr, String columnType) {
        return Arrays.asList(arr).contains(columnType);
    }

}
