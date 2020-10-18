package com.dandandog.framework.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author JohnnyLiu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheStorePut {

    @AliasFor("cacheName")
    String value() default "";

    @AliasFor("value")
    String cacheName() default "";

    String key() default "";

    long expired() default 5;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    boolean onlyStore() default true;
}
