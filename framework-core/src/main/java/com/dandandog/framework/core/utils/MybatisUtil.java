package com.dandandog.framework.core.utils;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.dandandog.framework.common.utils.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Component
public class MybatisUtil {

    public static <T> BaseMapper<T> getMapper(Class<T> typeClass) throws IllegalStateException, ClassNotFoundException {
        if (typeClass == null) {
            throw new IllegalStateException("typeClass is not null");
        }
        String classNamespace = SqlHelper.table(typeClass).getCurrentNamespace();
        Class<BaseMapper<T>> mapperClass = ClassUtil.loadClass(classNamespace);
        if (mapperClass == null) {
            throw new ClassNotFoundException("No baseMapper is defined by typeClass");
        }
        return SpringContextUtil.getBean(mapperClass);
    }

    public static <M extends BaseMapper<T>, T> ServiceImpl<M, T> getService(Class<T> typeClass) throws IllegalStateException {
        if (typeClass == null) {
            throw new IllegalStateException("typeClass is not null");
        }
        Map<String, ServiceImpl> beanMap = SpringContextUtil.getBeansOfType(ServiceImpl.class);
        ServiceImpl serviceImpl = null;
        for (ServiceImpl value : beanMap.values()) {
            if (ReflectionKit.getSuperClassGenericType(value.getClass(), 1) == typeClass) {
                serviceImpl = value;
            }
        }
        return serviceImpl;
    }

}
