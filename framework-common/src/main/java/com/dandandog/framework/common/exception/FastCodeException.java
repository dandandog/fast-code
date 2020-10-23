package com.dandandog.framework.common.exception;


import com.dandandog.framework.common.model.IError;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JohnnyLiu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FastCodeException extends RuntimeException {

    private String message;
    private Integer errorCode;
    private IError errorEnum;

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

    public FastCodeException(IError errorEnum) {
        super(errorEnum.getMsg());
        this.errorCode = errorEnum.getCode();
        this.message = errorEnum.getMsg();
        this.errorEnum = errorEnum;
    }


    public FastCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastCodeException(Throwable cause) {
        super(cause);
    }

}
