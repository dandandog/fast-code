package com.dandandog.framework.faces.exception;

import com.dandandog.framework.common.exception.FastCodeException;
import lombok.Getter;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author JohnnyLiu
 */
@Getter
public class MessageResolvableException extends FastCodeException {
    private static final long serialVersionUID = -5129058461881408125L;

    private final String category;

    private final Object[] parameters;

    public MessageResolvableException(String category, Integer errorCode) {
        this(category, errorCode, new Object[0], null);
    }

    public MessageResolvableException(String category, Integer errorCode, Object[] parameters) {
        this(category, errorCode, parameters, null);
    }

    public MessageResolvableException(String category, Integer errorCode, Throwable cause) {
        this(category, errorCode, null, cause);
    }

    public MessageResolvableException(String category, Integer errorCode, Object[] parameters, Throwable cause) {
        super(cause);
        this.category = category;
        super.setErrorCode(errorCode);
        this.parameters = parameters;
    }

    public String getMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(this.category + "." + this.getErrorCode(), this.parameters, locale);
    }
}
