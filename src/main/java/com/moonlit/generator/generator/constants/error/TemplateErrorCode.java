package com.moonlit.generator.generator.constants.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模板異常類
 *
 * @author Joshua
 * @version 1.0
 * @date 14/7/2022 23:46
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum TemplateErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    CREATE_TEMPLATE_FOLDER_ERROR(13000, "创建模板文件夹失败"),
    ;

    private Integer code;
    private String message;
}
