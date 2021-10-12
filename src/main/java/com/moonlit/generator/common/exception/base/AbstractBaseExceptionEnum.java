package com.moonlit.generator.common.exception.base;

/**
 * 异常枚举类
 *
 * @author tangjx
 * @date 2021/3/19 11:18
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
