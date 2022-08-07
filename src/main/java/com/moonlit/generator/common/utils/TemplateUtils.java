package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.constant.HljsConstants;

/**
 * 模板工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 11/7/2022 16:28
 * @email by.Moonlit@hotmail.com
 */
public class TemplateUtils {

    /**
     * 處理富文本標簽
     *
     * @param template 原始文本
     * @return 處理后的文本
     */
    public static String formatText(String template) {
        // 移除開頭與結尾
        for (String label : HljsConstants.HEAD_AND_END) {
            template = template.replace(label, CharacterConstant.EMPTY);
        }
        // 處理標簽
        for (String label : HljsConstants.LABEL) {
            template = template.replace(label, CharacterConstant.EMPTY);
        }
        // 處理左右標簽
        template = template.replace(HljsConstants.LT, CharacterConstant.LEFT_ANGLE_BRACKETS);
        template = template.replace(HljsConstants.GT, CharacterConstant.RIGHT_ANGLE_BRACKETS);
        // 處理空格
        template = template.replace(HljsConstants.SPACE, CharacterConstant.SPACE);
        return template;
    }

}
