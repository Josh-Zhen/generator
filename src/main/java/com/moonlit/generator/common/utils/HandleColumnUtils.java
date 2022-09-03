package com.moonlit.generator.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.constant.DatabaseConstants;
import com.moonlit.generator.common.constant.WebConstants;
import com.moonlit.generator.generator.entity.GenTableColumn;
import com.moonlit.generator.generator.entity.vo.TableFieldVO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 處理字段列工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 4/9/2022 0:16
 * @email by.Moonlit@hotmail.com
 */
public class HandleColumnUtils {

    /**
     * 初始化表字段實體
     *
     * @param tableId 表id
     * @param vos     字段信息
     * @return 篩選後的數據
     */
    public static ArrayList<GenTableColumn> initializeColumns(Long tableId, ArrayList<TableFieldVO> vos) {
        ArrayList<GenTableColumn> list = new ArrayList<>();

        for (TableFieldVO vo : vos) {
            // 列名
            String columnName = vo.getColumnName();

            // 填充字段
            GenTableColumn column = new GenTableColumn(tableId, vo);
            column.setJavaField(StringUtils.underlineToCamel(columnName));
            // 字段類型 (移除末尾的參數)
            String columnType = NamingStrategyUtils.substringBefore(vo.getColumnType(), CharacterConstant.LEFT_ROUND_BRACKETS);

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
                } else if (DatabaseConstants.DATABASE_NUMBER_TYPE[6].equals(columnType)
                        || StringUtils.endsWith(columnName, DatabaseConstants.COLUMN_NOT_QUERY[0])) {
                    // 主鍵或者包含id的字段
                    type = DatabaseConstants.LONG_TYPE;
                }
                column.setJavaType(type);
            }

            // 主鍵
            Boolean columnKey = vo.getColumnKey();
            // 排除主鍵
            if (!columnKey) {
                // 插入字段
                if (isContains(DatabaseConstants.DATABASE_DATE_TYPE, columnType)) {
                    column.setIsInsert(false);
                }
                // 編輯字段 (排除帶id結尾、時間類型)
                if (StringUtils.endsWith(columnName, DatabaseConstants.COLUMN_NOT_QUERY[0])
                        && isContains(DatabaseConstants.DATABASE_DATE_TYPE, columnType)) {
                    column.setIsEdit(false);
                }
                // 列表字段 (排除文本類型)
                if (isContains(DatabaseConstants.TEXT_TYPE, columnType)) {
                    column.setIsList(false);
                }
                // 查詢字段 (排除時間類型、文本類型)
                if (isContains(DatabaseConstants.DATABASE_DATE_TYPE, columnType)
                        || isContains(DatabaseConstants.TEXT_TYPE, columnType)
                        || isContains(DatabaseConstants.COLUMN_NOT_QUERY, columnName)) {
                    column.setIsQuery(false);
                }
            }
            // 主鍵列不可插入、編輯
            if (columnKey) {
                column.setIsInsert(false);
                column.setIsEdit(false);
            }

            // 查詢類型 (字段以name結尾的、文本類型)
            if (StringUtils.endsWith(columnName, "name") || isContains(DatabaseConstants.TEXT_TYPE, columnType)) {
                column.setQueryType(DatabaseConstants.CONDITION_TYPE[4]);
            }
            // 前端標簽
            if (ObjectUtil.isEmpty(column.getHtmlType())) {
                if (StringUtils.endsWith(columnName, WebConstants.STATE[0]) || StringUtils.endsWith(columnName, WebConstants.STATE[1])) {
                    // 單選框
                    column.setHtmlType(WebConstants.WEB_RADIO);
                } else if (StringUtils.endsWith(columnName, WebConstants.STATE[2]) || StringUtils.endsWith(columnName, WebConstants.STATE[3])) {
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
