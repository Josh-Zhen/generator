package com.moonlit.generator.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.moonlit.generator.common.constant.CharacterConstant;

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
            return StringUtils.underlineToCamel(name);
        }
        return StringUtils.underlineToCamel(removePrefix(name, tablePrefix));
    }

    /**
     * 取條件之前的字符串
     *
     * @param str       源字符串
     * @param separator 條件
     * @return 結果
     */
    public static String substringBefore(String str, String separator) {
        if (str.indexOf(separator) > 0 || !StringUtils.isEmpty(str)) {
            return str.substring(0, str.indexOf(separator));
        }
        return str;
    }
}
