package com.dandandog.framework.rest.exception;

import com.dandandog.framework.common.exception.FastCodeException;

public class ApiException extends FastCodeException {

    public ApiException(String msg) {
        super(msg);
    }
}
