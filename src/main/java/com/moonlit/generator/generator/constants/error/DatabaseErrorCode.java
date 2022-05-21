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
    DATABASE_NOT_EXIST(11001, "数据库不存在"),
    DATA_IS_TRUE(11002, "数据已存在，请检查是否存在同名数据"),
    DRIVEN_IS_NOT_EXIST(11003, "数据库驱动不存在"),
    SAVE_ERROR(11004, "添加表数据异常,请稍后重试！"),
    ;

    private Integer code;
    private String message;
}
