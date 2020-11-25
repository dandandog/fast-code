package com.dandandog.framework.rest.controller;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import com.dandandog.framework.common.model.IError;
import com.dandandog.framework.rest.model.ApiErrorCode;
import com.dandandog.framework.rest.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

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
    public ApiResult<IError> error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        switch (status) {
            case HttpServletResponse.SC_BAD_REQUEST:
                return ApiResult.failed(ApiErrorCode.LOGIN_EXCEPTION);
            case HttpServletResponse.SC_UNAUTHORIZED:
                return ApiResult.failed(ApiErrorCode.UNAUTHORIZED);
            case HttpServletResponse.SC_FORBIDDEN:
                return ApiResult.failed(ApiErrorCode.NOT_PERMISSION);
            case HttpServletResponse.SC_NOT_FOUND:
                return ApiResult.failed(ApiErrorCode.NOT_FOUND);
            default:
                IError error = Optional.ofNullable((IError) request.getAttribute(FastCodeConstant.ERROR_KEY)).orElse(ApiErrorCode.UNKNOWN);
                return ApiResult.failed(error, null);
        }
    }

    @Override
    public String getErrorPath() {
        log.error("errorPath....");
        return ERROR_PATH;
    }

}
