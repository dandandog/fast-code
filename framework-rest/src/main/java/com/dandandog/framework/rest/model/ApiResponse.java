package com.dandandog.framework.rest.model;

import com.dandandog.framework.common.model.IError;
import com.dandandog.framework.rest.exception.ApiException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Data
public class ApiResponse<T> implements Serializable {

    @ApiModelProperty(value = "自定义响应状态码", example = "20000")
    private long code;

    @ApiModelProperty(value = "响应消息", example = "操作成功")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;


    public ApiResponse() {
    }

    public ApiResponse(IError errorCode) {
        errorCode = (IError) Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }

        return restResult(data, aec);
    }

    public static <T> ApiResponse<T> failed(String msg, T data) {
        return restResult(data, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> ApiResponse<T> failed(String msg) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> ApiResponse<T> failed(IError errorCode, T data) {
        return restResult(data, errorCode);
    }

    public static <T> ApiResponse<T> failed(IError errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> ApiResponse<T> restResult(T data, IError errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> ApiResponse<T> restResult(T data, long code, String msg) {
        ApiResponse<T> apiResult = new ApiResponse<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == this.code;
    }

    public T serviceData() {
        if (!this.ok()) {
            throw new ApiException(this.msg);
        } else {
            return this.data;
        }
    }

    public long getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public ApiResponse<T> setCode(final long code) {
        this.code = code;
        return this;
    }

    public ApiResponse<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public ApiResponse<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApiResponse;
    }

}
