package com.moonlit.generator.common.exception.base;

/**
 * 异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 错误码
     *
     * @return 编码
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return 信息
     */
    String getMessage();
}
