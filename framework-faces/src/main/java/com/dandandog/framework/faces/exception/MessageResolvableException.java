package com.dandandog.framework.faces.exception;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author create by Johnny
 * @description com.johnny.web.exception
 * @date 七月 06,2019
 */
public class MessageResolvableException extends RuntimeException {
    private static final long serialVersionUID = -5129058461881408125L;
    private String category;
    private String errorCode;
    private Object[] parameters;

    public MessageResolvableException(String category, String errorCode) {
        this(category, errorCode, new Object[0], null);
    }

    public MessageResolvableException(String category, String errorCode, Object[] parameters) {
        this(category, errorCode, parameters, null);
    }

    public MessageResolvableException(String category, String errorCode, Throwable cause) {
        this(category, errorCode, null, cause);
    }

    public MessageResolvableException(String category, String errorCode, Object[] parameters, Throwable cause) {
        super(cause);
        this.category = category;
        this.errorCode = errorCode;
        this.parameters = parameters;
    }

    public String getCategory() {
        return this.category;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public String getMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(this.category + "." + this.errorCode, this.parameters, locale);
    }
}
