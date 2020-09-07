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

    private final String errorCode;

    private final Object[] parameters;

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

    public String getMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(this.category + "." + this.errorCode, this.parameters, locale);
    }
}
