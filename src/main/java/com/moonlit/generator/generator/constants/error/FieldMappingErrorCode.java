package com.moonlit.generator.generator.constants.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 键值映射異常
 *
 * @author Joshua
 * @version 1.0
 * @date 14/9/2022 23:49
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum FieldMappingErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    COMMENT_IS_EXIST(14000, "存在已启用的键"),
    ;

    private Integer code;
    private String message;
}
