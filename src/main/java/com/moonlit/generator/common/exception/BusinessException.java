package com.moonlit.generator.common.exception;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author tangjx
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

    public BusinessException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public BusinessException(int index, String message) {
        super(message);
        this.code = index;
        this.message = message;
    }

    public BusinessException(Integer code, String message, Throwable e) {
        super(message);
        this.code = code;
        this.message = message;
        this.e = e;
    }
}