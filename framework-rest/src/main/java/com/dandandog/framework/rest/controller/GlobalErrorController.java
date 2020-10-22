package com.dandandog.framework.rest.controller;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import com.dandandog.framework.common.exception.TokenException;
import com.dandandog.framework.common.model.ApiErrorCode;
import com.dandandog.framework.rest.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author JohnnyLiu
 */
@ApiIgnore
@RestController
@ControllerAdvice
@Slf4j
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    @ResponseStatus(value = OK)
    public ApiResponse error(HttpServletRequest request, HttpServletResponse response) {
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

        ApiErrorCode exception = (ApiErrorCode) request.getAttribute(FastCodeConstant.ERROR_KEY);
        if (exception == null) {
            exception = ApiErrorCode.UNKNOWN;
        }

        return ApiResponse.failed(exception);
    }

    @Override
    public String getErrorPath() {
        log.error("errorPath....");
        return ERROR_PATH;
    }

    @ExceptionHandler(TokenException.class)
    public ApiResponse customException(TokenException e) {
        return ApiResponse.failed(e.getApiErrorCode());
    }

}
