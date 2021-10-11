package com.moonlit.generator.common.exception.base;

/**
 * @author tangjx
 * @date 2021/3/19 11:18
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 错误码
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMessage();
}
