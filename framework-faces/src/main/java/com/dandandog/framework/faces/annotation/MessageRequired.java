package com.dandandog.framework.faces.annotation;

import java.lang.annotation.*;

/**
 * @author JohnnyLiu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageRequired {

    MessageType type() default MessageType.OPERATION;

    boolean errorOnly() default false;
}
