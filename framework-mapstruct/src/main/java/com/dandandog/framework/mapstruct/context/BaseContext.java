package com.dandandog.framework.mapstruct.context;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.TypeUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/**
 * @author JohnnyLiu
 */
@SuppressWarnings("unchecked")
public interface BaseContext<T> {


    /**
     * 实体映射前
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @BeforeMapping
    default void beforeMapping(@MappingTarget Object target, @TargetType Class<?> t) {
        Class<?> typeArgument = ClassUtil.getTypeArgument(this.getClass());
        if (ObjectUtil.equal(t, typeArgument) && ObjectUtil.equal(target.getClass(), typeArgument)) {
            before((T) target, (Class<T>) t);
        }
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
        Class<?> typeArgument = ClassUtil.getTypeArgument(this.getClass());
        if (ObjectUtil.equal(t, typeArgument) && ObjectUtil.equal(target.getClass(), typeArgument)) {
            after((T) target, (Class<T>) t);
        }
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
