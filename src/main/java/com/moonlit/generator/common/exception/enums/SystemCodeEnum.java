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
public enum SystemCodeEnum implements AbstractBaseExceptionEnum {

    /**
     * 字典类型不存在
     */
    DICT_TYPE_NOT_EXIST(1, "字典类型不存在"),
    INSERT_EXCEPTION(12001, "新增异常,请稍后重试"),
    UPDATE_EXCEPTION(12002, "更新异常,请稍后重试"),
    ;
    private Integer code;
    private String message;

}
