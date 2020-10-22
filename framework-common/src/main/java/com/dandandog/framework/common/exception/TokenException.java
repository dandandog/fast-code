package com.dandandog.framework.common.exception;

import com.dandandog.framework.common.model.ApiErrorCode;

/**
 * @author JohnnyLiu
 */
public class TokenException extends FastCodeException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public TokenException(ApiErrorCode apiCode) {
        super(apiCode);
    }
}
