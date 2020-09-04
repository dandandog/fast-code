package com.dandandog.framework.common.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JohnnyLiu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FastCodeException extends RuntimeException {

    private Integer errorCode;
    private String message;

    public FastCodeException() {
        super();
    }

    public FastCodeException(String message) {
        super(message);
        this.message = message;
    }

    public FastCodeException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public FastCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastCodeException(Throwable cause) {
        super(cause);
    }

}
