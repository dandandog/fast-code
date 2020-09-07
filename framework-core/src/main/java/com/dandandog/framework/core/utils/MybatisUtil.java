package com.dandandog.framework.core.utils;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.dandandog.framework.common.utils.SpringContextUtil;
import org.springframework.stereotype.Component;

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


}
