package com.moonlit.generator.generator.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.generator.entity.GenTables;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 生成表工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/11 14:41
 * @email by.Moonlit@hotmail.com
 */
public class GenTableUtils {

    /**
     * 初始化表實體
     *
     * @param databaseId   數據庫id
     * @param tableName    表名
     * @param removePrefix 移除前綴\
     * @return 表實體
     */
    public GenTables initializeTable(Long databaseId, String tableName, String tableComment, boolean removePrefix) {
        GenTables genTables = new GenTables(databaseId, tableName, tableComment);
        genTables.setClassName(convertClassName(removePrefix, tableName));
//        genTables.setBusinessName();
//        genTables.setFunctionName();
        return genTables;
    }

    /**
     * 表名轉類名
     *
     * @param removePrefix 移除前綴
     * @return 類名稱
     */
    private static String convertClassName(boolean removePrefix, String tableName) {
        if (removePrefix) {

        }

        return "";
    }



}
