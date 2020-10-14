package com.dandandog.framework.cache.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.jcache.interceptor.JCacheOperation;
import org.springframework.cache.jcache.interceptor.JCacheOperationSource;

import java.lang.reflect.Method;

/**
 * @author JohnnyLiu
 */
public class FastKeyGenerator implements KeyGenerator {

    private final JCacheOperationSource cacheOperationSource;

    public FastKeyGenerator(JCacheOperationSource cacheOperationSource) {
        this.cacheOperationSource = cacheOperationSource;
    }

    @Override
    public Object generate(Object target, Method method, Object... objects) {
        JCacheOperation<?> operation = this.cacheOperationSource.getCacheOperation(method, target.getClass());
        return "";
    }


}
