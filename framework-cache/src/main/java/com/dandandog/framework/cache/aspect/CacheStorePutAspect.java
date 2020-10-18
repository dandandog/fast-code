package com.dandandog.framework.cache.aspect;

import com.dandandog.framework.cache.annotation.CacheStorePut;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class CacheStorePutAspect extends AbstractCacheStoreAspect {

    RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(com.dandandog.framework.cache.annotation.CacheStorePut)")
    public Object storePut(ProceedingJoinPoint point) throws Throwable {
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            CacheStorePut cacheStore = method.getAnnotation(CacheStorePut.class);
            String key = keyGenerator(cacheStore.value(), cacheStore.key(), point);
            Object value;
            if (cacheStore.checkStore() && Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false)) {
                return redisTemplate.opsForValue().get(key);
            }
            value = point.proceed();
            redisTemplate.opsForValue().set(key, value, cacheStore.expired(), cacheStore.timeUnit());
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
