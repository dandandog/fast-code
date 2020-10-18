package com.dandandog.framework.cache.aspect;

import com.dandandog.framework.cache.annotation.CacheStoreEvict;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class CacheStoreEvictAspect extends AbstractCacheStoreAspect {


    RedisTemplate<String, Object> redisTemplate;

    @Before("@annotation(com.dandandog.framework.cache.annotation.CacheStoreEvict)")
    public void storeEvict(JoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        CacheStoreEvict cacheStore = method.getAnnotation(CacheStoreEvict.class);
        for (String key : cacheStore.keys()) {
            key = keyGenerator(cacheStore.cacheName(), key, point);
            Set<String> deleteKeys = Optional.ofNullable(redisTemplate.keys(key)).orElse(Collections.emptySet());
            redisTemplate.delete(deleteKeys);
        }
    }
}
