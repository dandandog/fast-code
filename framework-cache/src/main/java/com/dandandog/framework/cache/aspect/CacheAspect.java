package com.dandandog.framework.cache.aspect;

import com.dandandog.framework.cache.annotation.CacheCheck;
import com.dandandog.framework.cache.annotation.CacheRemove;
import com.dandandog.framework.cache.annotation.CacheUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author JohnnyLiu
 */

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class CacheAspect {

    RedisTemplate<String, Object> redisTemplate;


    @Around("@annotation(com.dandandog.framework.cache.annotation.CacheCheck)")
    public Object add(ProceedingJoinPoint point) throws Throwable {
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            CacheCheck cacheAdd = method.getAnnotation(CacheCheck.class);
            String key = cacheAdd.value();
            if (key.contains("#")) {
                key = parseKey(key, method, point.getArgs());
            }
            if (Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false)) {
                return redisTemplate.opsForValue().get(key);
            }
            Object value = point.proceed();
            redisTemplate.opsForValue().set(key, value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Around("@annotation(com.dandandog.framework.cache.annotation.CacheUpdate)")
    public Object update(ProceedingJoinPoint point) throws Throwable {
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            CacheUpdate cacheUpdate = method.getAnnotation(CacheUpdate.class);
            String key = cacheUpdate.value();
            if (key.contains("#")) {
                key = parseKey(key, method, point.getArgs());
            }
            Object value = point.proceed();
            redisTemplate.opsForValue().set(key, value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Before("@annotation(com.dandandog.framework.cache.annotation.CacheRemove)")
    public void remove(JoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);
        String[] keys = cacheRemove.value();
        for (String key : keys) {
            if (key.contains("#")) {
                key = parseKey(key, method, point.getArgs());
            }
            Set<String> deleteKeys = Optional.ofNullable(redisTemplate.keys(key)).orElse(Collections.emptySet());
            redisTemplate.delete(deleteKeys);
            log.debug("cache key: " + key + " deleted");
        }
    }

    /**
     * parseKey from SPEL
     */
    private String parseKey(String key, Method method, Object[] args) {
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
