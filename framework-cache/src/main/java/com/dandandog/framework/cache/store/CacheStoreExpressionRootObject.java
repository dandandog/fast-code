package com.dandandog.framework.cache.store;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author JohnnyLiu
 */
@Data
public class CacheStoreExpressionRootObject {

    private final Method method;

    private final Object[] args;

    private final Object target;

    private final Class<?> targetClass;

}
