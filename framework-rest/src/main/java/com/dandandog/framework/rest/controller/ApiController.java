package com.dandandog.framework.rest.controller;

import com.dandandog.framework.common.model.IError;
import com.dandandog.framework.rest.model.ApiResponse;

public class ApiController {

    protected <T> ApiResponse<T> success(T data) {
        return ApiResponse.ok(data);
    }

    protected <T> ApiResponse<T> failed(String msg) {
        return ApiResponse.failed(msg);
    }

    protected <T> ApiResponse<T> failed(IError errorCode) {
        return ApiResponse.failed(errorCode);
    }
}
