package com.dandandog.framework.common.exception;

import com.dandandog.framework.common.model.ApiErrorCode;

/**
 * @author Jirath
 * @date 2020/4/9
 * @description:
 */
public class TokenException extends FastCodeException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public TokenException(ApiErrorCode apiCode) {
        super(apiCode.getMsg());
    }
}
