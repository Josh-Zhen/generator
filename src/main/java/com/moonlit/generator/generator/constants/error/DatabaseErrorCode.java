package com.moonlit.generator.generator.constants.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 數據庫異常
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/11/25 10:18
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum DatabaseErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    UNABLE_TO_CONNECT(11001, "无法连接库"),
    DATA_IS_TRUE(11002, "数据已存在，请检查是否存在同名数据"),
    DRIVEN_IS_NOT_EXIST(11003, "数据库驱动不存在"),
    ;

    private Integer code;
    private String message;
}
