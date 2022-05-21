package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 命名工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/11 17:19
 * @email by.Moonlit@hotmail.com
 */
public class NamingStrategy {

    /**
     * 下劃綫轉駝峰
     *
     * @param name 字符
     * @return 駝峰字符
     */
    public static String underlineToCamel(String name) {
        if (StringUtils.isBlank(name)) {
            return CharacterConstant.EMPTY;
        }

        // 是否包含大寫
        if (isMixedIncludeCase(name)) {
            name = name.toLowerCase();
        }

        StringBuilder result = new StringBuilder();
        // 處理字段
        Arrays.stream(name.split(CharacterConstant.UNDER_LINE)).filter(camel -> !camel.isEmpty()).forEach(camel -> {
            // 填充第一段
            if (result.length() == 0) {
                result.append(camel);
            } else {
                // 填充剩下部分
                result.append(firstToUpperCase(camel));
            }
        });
        return result.toString();
    }

    /**
     * 首字母轉大寫
     *
     * @return 結果
     */
    public static String firstToUpperCase(String name) {
        if (StringUtils.isNotBlank(name)) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return CharacterConstant.EMPTY;
    }

    /**
     * 去掉指定的前缀
     *
     * @param name   字段
     * @param prefix 前綴
     * @return 結果
     */
    public static String removePrefix(String name, String prefix) {
        return name.replaceFirst(prefix, "");
    }

    /**
     * 去掉下划线前缀且将后半部分转成驼峰格式
     *
     * @param name        表名
     * @param tablePrefix 表前綴
     * @return 結果
     */
    public static String removePrefixAndCamel(String name, String tablePrefix) {
        if (!name.contains(tablePrefix)) {
            return underlineToCamel(name);
        }
        return underlineToCamel(removePrefix(name, tablePrefix));
    }

    /**
     * 是否包含大寫
     *
     * @param cs 字符串
     * @return 結果
     */
    public static boolean isMixedIncludeCase(final CharSequence cs) {
        if (StringUtils.isBlank(cs) || cs.length() == 1) {
            return false;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (Character.isUpperCase(cs.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
