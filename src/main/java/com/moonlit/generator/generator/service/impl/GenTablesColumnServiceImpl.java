package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.util.StringUtils;

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
     * 根据主键查询
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public GenTablesColumn getGenTables(Integer id) {
        return this.getById(id);
    }

    /**
     * 匹配數據類型
     *
     * @param columnType 數據庫類型
     * @return java數據類型
     */
    private static String matchDataType(String columnType) {


        return columnType;
    }

    /**
     * 判斷數據類型
     *
     * @param arr        類型數組
     * @param columnType 字段類型
     * @return 結果
     */
    private static Boolean judgeType(String[] arr, String columnType) {
        return Arrays.asList(arr).contains(columnType);
    }

    /**
     * 修改
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    @Override
    public Boolean updateTablesColumn(GenTablesColumn genTablesColumn) {
        genTablesColumn.setUpdateTime(LocalDateTime.now());
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
     * 新增
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
            ArrayList<GenTablesColumn> list = filterData(saveDTO.getTableId(), vos);
            if (list.size() > 0) {
                return this.saveBatch(list);
            }
        } catch (Exception e) {
            throw new BusinessException(DatabaseErrorCode.SAVE_ERROR);
        }
        return true;
    }

    /**
     * 篩選數據是否已經儲存
     *
     * @param tableId 表id
     * @param vos     字段信息
     * @return 篩選後的數據
     */
    private ArrayList<GenTablesColumn> filterData(Long tableId, ArrayList<TableFieldVO> vos) {
        ArrayList<GenTablesColumn> list = new ArrayList<>();
        Collection<String> tablesColumns = baseMapper.selectTablesName(tableId);

        if (tablesColumns.size() > 0) {
            for (TableFieldVO vo : vos) {
                // 列名
                String columnName = vo.getColumnName();
                // 數據不包含這個字段
                if (!tablesColumns.contains(columnName)) {
                    // 填充字段
                    GenTablesColumn tablesColumn = new GenTablesColumn(tableId, vo);
                    tablesColumn.setJavaField(NamingStrategy.underlineToCamel(columnName));
                    String columnType = vo.getColumnType();
                    tablesColumn.setJavaType(matchDataType(columnType));

                    // 主鍵
                    Boolean columnKey = vo.getColumnKey();
                    // 新增字段
                    if (!columnKey || judgeType(DatabaseConstants.DATABASE_DATE_TYPE, columnType)) {
                        tablesColumn.setIsInsert(false);
                    }
                    // 編輯字段
                    if (!columnKey && judgeType(DatabaseConstants.DATABASE_STRING_TYPE, columnType)) {
                        tablesColumn.setIsEdit(false);
                    }
                    // 列表字段
                    if (!columnKey && judgeType(DatabaseConstants.DATABASE_NUMBER_TYPE, columnType)) {
                        tablesColumn.setIsList(false);
                    }
                    // 查詢字段
//                    if () {
//                        tablesColumn.setIsQuery(false);
//                    }
                    // 查詢類型
//                    if () {
//                        tablesColumn.setQueryType("like");
//                    }

                    // 字典類型例如：sex、status、type等
                    if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
                        tablesColumn.setHtmlType(WebConstants.WEB_RADIO);
                    }
                    // 类型&性别字段设置下拉框
                    else if (StringUtils.endsWithIgnoreCase(columnName, "type") || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
                        tablesColumn.setHtmlType(WebConstants.WEB_SELECT);
                    }

                    list.add(tablesColumn);
                }
            }
        }
        return list;
    }

}
