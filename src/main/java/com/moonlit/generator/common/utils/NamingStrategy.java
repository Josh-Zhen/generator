package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import org.apache.commons.lang3.StringUtils;

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
        // 快速检查
        if (StringUtils.isBlank(name)) {
            // 没必要转换
            return CharacterConstant.EMPTY;
        } else if (!name.contains(CharacterConstant.UNDERLINE)) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }

        StringBuilder result = new StringBuilder();
        // 用下划线将原始字符串分割
        for (String camel : name.split(CharacterConstant.UNDERLINE)) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 去掉指定的前缀
     *
     * @param name   ignore
     * @param prefix ignore
     * @return ignore
     */
    public static String removePrefix(String name, String prefix) {
        return name.replaceFirst(prefix, "");
    }

    /**
     * 去掉下划线前缀且将后半部分转成驼峰格式
     *
     * @param name        ignore
     * @param tablePrefix ignore
     * @return ignore
     */
    public static String removePrefixAndCamel(String name, String tablePrefix) {
        return underlineToCamel(removePrefix(name, tablePrefix));
    }

    /**
     * 实体首字母大写
     *
     * @param name 待转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalFirst(String name) {
        if (StringUtils.isNotBlank(name)) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return CharacterConstant.EMPTY;
    }


}
