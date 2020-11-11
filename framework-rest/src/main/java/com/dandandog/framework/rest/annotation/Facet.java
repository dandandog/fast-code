package com.dandandog.framework.rest.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author JohnnyLiu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Facet {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
