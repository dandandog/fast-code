package com.dandandog.framework.common.exception;


import com.dandandog.framework.common.model.ApiErrorCode;
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
    private ApiErrorCode apiErrorCode;

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

    public FastCodeException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode.getMsg());
        this.apiErrorCode = apiErrorCode;
    }


    public FastCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastCodeException(Throwable cause) {
        super(cause);
    }

}
