package com.dandandog.framework.mapstruct.annotation;

import org.mapstruct.Mapper;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author JohnnyLiu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
@Mapper
public @interface RegisterMapper {

    @AliasFor(annotation = Mapper.class, attribute = "uses")
    String[] uses() default {};

    @AliasFor(annotation = Mapper.class, attribute = "componentModel")
    String componentModel() default "spring";

    @AliasFor(annotation = Mapper.class, attribute = "imports")
    Class<?>[] imports() default {};

}
