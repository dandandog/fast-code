package com.dandandog.framework.mapstruct.context;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/**
 * @author JohnnyLiu
 */
public interface BaseContext<T> {


    /**
     * 实体映射前
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @BeforeMapping
    default void beforeMapping(@MappingTarget Object target, @TargetType Class<?> t) {
        before((T) target, (Class<T>) t);
    }

    /**
     * 实现泛型转换
     *
     * @param target
     * @param t
     */
    default void before(T target, Class<T> t) {

    }


    /**
     * 实体映射后
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @AfterMapping
    default void afterMapping(@MappingTarget Object target, @TargetType Class<?> t) {
        after((T) target, (Class<T>) t);
    }

    /**
     * 实现泛型转换
     *
     * @param target
     * @param t
     */
    default void after(T target, Class<T> t) {
    }
}
