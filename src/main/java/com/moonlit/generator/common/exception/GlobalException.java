package com.moonlit.generator.common.exception;

import com.moonlit.generator.common.exception.enums.ErrorCodeEnum;
import com.moonlit.generator.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常类
 *
 * @author tangjx
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    /**
     * 全局异常处理
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception ex) {
        log.error("exceptionHandler: ", ex);
        String msg = specialExceptionResolve(ex) == null ? "哎呀，出问题啦" : specialExceptionResolve(ex);
        return Result.fail(ErrorCodeEnum.DEFAULT_ERROR.getCode(), msg);
    }

    /**
     * 系统自定义异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public Result handleMicrosException(BusinessException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 这个方法是拷贝
     * 加入自定义处理，实现对400， 404， 405， 406， 415， 500(参数问题导致)， 503的处理
     *
     * @param ex 异常信息
     * @return
     */
    private String specialExceptionResolve(Exception ex) {
        if (ex instanceof NoHandlerFoundException) {
            return ex.getMessage();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return ex.getMessage();
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return ex.getMessage();
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return ex.getMessage();
        } else if (ex instanceof MissingPathVariableException) {
            return ex.getMessage();
        } else if (ex instanceof MissingServletRequestParameterException) {
            return ex.getMessage();
        } else if (ex instanceof ServletRequestBindingException) {
            return ex.getMessage();
        } else if (ex instanceof ConversionNotSupportedException) {
            return ex.getMessage();
        } else if (ex instanceof TypeMismatchException) {
            return ex.getMessage();
        } else if (ex instanceof HttpMessageNotReadableException) {
            return ex.getMessage();
        } else if (ex instanceof HttpMessageNotWritableException) {
            return ex.getMessage();
        } else if (ex instanceof MissingServletRequestPartException) {
            return ex.getMessage();
        } else if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
            return null;
        } else if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
            return null;
        } else if (ex instanceof AsyncRequestTimeoutException) {
            return ex.getMessage();
        }
        return null;
    }
}
