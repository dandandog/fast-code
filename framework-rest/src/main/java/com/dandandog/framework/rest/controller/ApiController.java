package com.dandandog.framework.rest.controller;

import com.dandandog.framework.common.model.IError;
import com.dandandog.framework.rest.model.ApiResult;

/**
 * @author Johnny
 */
public class ApiController {

    protected <T> ApiResult<T> success(T data) {
        return ApiResult.success(data);
    }

    protected <T> ApiResult<T> failed(String msg) {
        return ApiResult.failed(msg);
    }

    protected <T> ApiResult<T> failed(IError errorCode) {
        return ApiResult.failed(errorCode);
    }
}
