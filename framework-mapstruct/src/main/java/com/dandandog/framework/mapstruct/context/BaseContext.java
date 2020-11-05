package com.dandandog.framework.mapstruct.context;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/**
 * @author JohnnyLiu
 */
public interface BaseContext {


    /**
     * 映射前
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @BeforeMapping
    default void before(@MappingTarget Object target, @TargetType Class<?> t) {

    }

    /**
     * 映射后
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @AfterMapping
    default void after(@MappingTarget Object target, @TargetType Class<?> t) {
    }
}
