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
public abstract class BaseContext<T> extends AbstractContext<T> {


    /**
     * 实体映射前
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @BeforeMapping
    public void enterBefore(@MappingTarget Object target, @TargetType Class<?> t) {
        Class<?> typeArgument = ClassUtil.getTypeArgument(this.getClass());
        if (ObjectUtil.equal(t, typeArgument) && ObjectUtil.equal(target.getClass(), typeArgument)) {
            before((T) target, (Class<T>) t);
        }
    }


    /**
     * 实体映射后
     *
     * @param target 映射对象
     * @param t      映射类
     */
    @AfterMapping
    public void enterAfter(@MappingTarget Object target, @TargetType Class<?> t) {
        Class<?> typeArgument = ClassUtil.getTypeArgument(this.getClass());
        if (ObjectUtil.equal(t, typeArgument) && ObjectUtil.equal(target.getClass(), typeArgument)) {
            after((T) target, (Class<T>) t);
        }
    }
}
