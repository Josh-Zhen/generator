package com.moonlit.generator.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果集
 *
 * @author tangjx
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer CODE_200 = 200;

    public static final Integer CODE_500 = 500;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 返回的数据
     */
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(CODE_200);
        result.setMessage("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(CODE_200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(CODE_500);
        result.setMessage("fail");
        return result;
    }

    public static <T> Result<T> fail(String errorMsg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(CODE_500);
        result.setMessage(errorMsg);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
