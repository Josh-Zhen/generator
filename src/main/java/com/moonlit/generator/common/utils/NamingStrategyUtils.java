package com.moonlit.generator.common.utils;

import cn.hutool.core.util.ObjectUtil;
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
public class NamingStrategyUtils {

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
     * 取條件之前的字符串
     *
     * @param str       源字符串
     * @param separator 條件
     * @return 結果
     */
    public static String substringBefore(String str, String separator) {
        if (str.indexOf(separator) > 0 && !StringUtils.isEmpty(str)) {
            return str.substring(0, str.indexOf(separator));
        }
        return str;
    }

    /**
     * 获取业务名
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf(CharacterConstant.UNDER_LINE);
        return tableName.substring(lastIndex + 1);
    }

    /**
     * 獲取業務描述
     *
     * @param tableComment 表描述
     * @return 業務描述
     */
    public static String getBusinessComment(String tableComment) {
        String businessName = "";
        if (ObjectUtil.isNotEmpty(tableComment)) {
            int index = tableComment.length() - 1;
            businessName = tableComment.substring(index).contains("表") ? tableComment.substring(0, index) : tableComment;
        }
        return businessName;
    }

}
