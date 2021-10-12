package com.moonlit.generator.common.exception.enums;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义异常枚举类
 *
 * @author tangjx
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum implements AbstractBaseExceptionEnum {

    //系统示知错误
    DEFAULT_ERROR(-1, "系统未知错误"),
    //成功
    SUCCESS(0, "成功"),
    //对象为空
    OBJECT_EMPTY(1, "对象为空"),
    ;
    
    private Integer code;
    private String message;
}
