package com.moonlit.generator.generator.constants.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表配置異常
 *
 * @author Joshua
 * @version 1.0
 * @date 14/7/2022 22:49
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum TableConfigErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    DEFAULT_AUTHOR_CONFIGURATION_NOT_FOUND(12000, "未找到默认的作者配置"),
    ;

    private Integer code;
    private String message;
}
