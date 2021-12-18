package com.moonlit.generator.generator.constant;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joshua
 * @version 1.0
 * @date 2021/11/25 10:18
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum DbErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    GENERATE_FAIL(11001, "生成库失败，无法连接库"),
    DATA_IS_TRUE(11002, "数据已存在，请检查是否存在同名数据"),
    ;

    private Integer code;

    private String message;
}
