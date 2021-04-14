package com.dandandog.framework.mapstruct.context;

/**
 * @author JohnnyLiu
 */
public abstract class AbstractContext<T> {

    protected void before(T target, Class<T> t) {
    }


    protected void after(T target, Class<T> t) {
    }
}
