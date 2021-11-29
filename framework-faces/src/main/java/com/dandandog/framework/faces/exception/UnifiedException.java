package com.dandandog.framework.faces.exception;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/11/11 17:21
 */
public class UnifiedException extends RuntimeException {

    public UnifiedException(Throwable t) {
        addSuppressed(t);
    }

}
