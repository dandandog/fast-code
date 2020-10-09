package com.dandandog.framework.captcha.exception;

import com.dandandog.framework.common.exception.FrameworkException;

public class CaptchaVerifyException extends FrameworkException {

    public CaptchaVerifyException(String message) {
        super(message);
    }
}
