package com.moonlit.generator.system.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum SystemCodeEnum implements AbstractBaseExceptionEnum {

    /**
     * 字典类型不存在
     */
    DICT_TYPE_NOT_EXIST(10001, "字典类型不存在"),
    INSERT_EXCEPTION(10002, "新增异常,请稍后重试"),
    UPDATE_EXCEPTION(10003, "更新异常,请稍后重试"),
    NAME_ALREADY_EXISTS(10004, "名称已存在"),
    VALUE_ALREADY_EXISTS(10005, "键已存在");
    private Integer code;
    private String message;

}
