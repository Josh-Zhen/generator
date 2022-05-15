package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.exception.enums.ErrorCodeEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServlet工具类，获取当前request和response
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:59
 * @email by.Moonlit@hotmail.com
 */
public class HttpServletUtil {

    /**
     * 获取当前请求的request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(ErrorCodeEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(ErrorCodeEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getResponse();
        }
    }
}
