package com.moonlit.generator.common.exception;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import com.moonlit.generator.common.response.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private Throwable e;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 自定義異常
     *
     * @param exception 自定義異常類
     */
    public BusinessException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    /**
     * java異常
     *
     * @param e Java異常類
     */
    public BusinessException(Exception e) {
        super(e);
        this.code = Result.CODE_500;
        this.message = e.getMessage();
    }

    public BusinessException(Integer code, String message, Throwable e) {
        super(message);
        this.code = code;
        this.message = message;
        this.e = e;
    }
}