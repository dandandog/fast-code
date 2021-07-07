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

    MessageSeverity[] severity() default {MessageSeverity.INFO, MessageSeverity.WARN, MessageSeverity.ERROR, MessageSeverity.FATAL};

    MessageNotice notice() default MessageNotice.DEFAULT;
}
