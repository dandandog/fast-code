package com.dandandog.framework.rest.controller;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import com.dandandog.framework.common.model.IError;
import com.dandandog.framework.rest.model.ApiErrorCode;
import com.dandandog.framework.rest.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author JohnnyLiu
 */
@ApiIgnore
@RestController
@Slf4j
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    @ResponseStatus(value = OK)
    public ApiResponse<IError> error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        switch (status) {
            case HttpServletResponse.SC_BAD_REQUEST:
                return ApiResponse.failed(ApiErrorCode.LOGIN_EXCEPTION);
            case HttpServletResponse.SC_UNAUTHORIZED:
                return ApiResponse.failed(ApiErrorCode.UNAUTHORIZED);
            case HttpServletResponse.SC_FORBIDDEN:
                return ApiResponse.failed(ApiErrorCode.NOT_PERMISSION);
            case HttpServletResponse.SC_NOT_FOUND:
                return ApiResponse.failed(ApiErrorCode.NOT_FOUND);
            default:
                break;
        }

        IError exception = (IError) request.getAttribute(FastCodeConstant.ERROR_KEY);
        if (exception == null) {
            exception = ApiErrorCode.UNKNOWN;
        }

        return ApiResponse.failed(exception, null);
    }

    @Override
    public String getErrorPath() {
        log.error("errorPath....");
        return ERROR_PATH;
    }

}
