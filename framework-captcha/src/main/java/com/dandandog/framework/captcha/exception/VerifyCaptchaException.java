package com.dandandog.framework.captcha.exception;

import org.springframework.security.core.AuthenticationException;

public class VerifyCaptchaException extends AuthenticationException {

    public VerifyCaptchaException(String message) {
        super(message);
    }
}
