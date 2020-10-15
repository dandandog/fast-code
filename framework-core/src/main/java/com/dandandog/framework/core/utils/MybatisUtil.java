package com.dandandog.framework.core.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.common.model.IEntity;
import com.dandandog.framework.common.utils.SpringContextUtil;
import com.dandandog.framework.core.entity.BaseEntity;
import com.dandandog.framework.core.service.BaseServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
@Component
public class MybatisUtil {

    public static <T> BaseMapper<T> getOneMappersByModelClass(Class<T> typeClass) throws IllegalStateException {
        List<BaseMapper<T>> baseMappers = getMappersByModelClass(typeClass);
        return baseMappers.stream().findFirst().orElse(null);
    }

    public static <T> List getMappersByModelClass(Class<T> typeClass) throws IllegalStateException {
        List<BaseServiceImpl> baseMappers = getServicesByModelClass(typeClass);
        return baseMappers.stream().map(ServiceImpl::getBaseMapper).collect(Collectors.toList());
    }

    public static <M extends BaseMapper<T>, T extends IEntity> BaseServiceImpl<M, T> getOneServiceByModelClass(Class<T> typeClass) throws IllegalStateException {
        List<BaseServiceImpl> baseServices = getServicesByModelClass(typeClass);
        return baseServices.stream().findFirst().orElse(null);
    }

    public static <M extends BaseMapper<T>, T> List<BaseServiceImpl> getServicesByModelClass(Class<T> typeClass) throws IllegalStateException {
        if (typeClass == null) {
            throw new IllegalStateException("typeClass is not null");
        }
        Map<String, BaseServiceImpl> beanMap = SpringContextUtil.getBeansOfType(BaseServiceImpl.class);
        if (CollUtil.isEmpty(beanMap)) {
            return Collections.emptyList();
        }
        return beanMap.values().stream().filter(baseService -> baseService.isCurrentModelClass(typeClass)).collect(Collectors.toList());
    }


}
