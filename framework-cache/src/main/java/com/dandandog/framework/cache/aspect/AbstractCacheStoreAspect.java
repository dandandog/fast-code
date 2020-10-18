package com.dandandog.framework.cache.aspect;

import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.cache.store.CacheStoreExpressionRootObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Objects;

public abstract class AbstractCacheStoreAspect {


    protected String keyGenerator(String cacheName, String key, JoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Object target = point.getTarget();
        Class<?> targetClass = target.getClass();

        StringBuilder sb = new StringBuilder(cacheName);
        sb.append("::").append(ClassUtil.getClassName(targetClass, true)).append(":");

        CacheStoreExpressionRootObject rootObject = new CacheStoreExpressionRootObject(method, point.getArgs(), target, targetClass);
        if (key.contains("#")) {
            sb.append(parseKey(key, rootObject));
        }
        return sb.toString();
    }

    private String parseKey(String value, CacheStoreExpressionRootObject rootObject) {
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(rootObject.getMethod());

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setRootObject(rootObject);
        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            context.setVariable(paraNameArr[i], rootObject.getArgs()[i]);
        }
        return Objects.requireNonNull(parser.parseExpression(value).getValue(context)).toString();
    }
}
