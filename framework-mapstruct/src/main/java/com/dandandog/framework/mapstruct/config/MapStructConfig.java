package com.dandandog.framework.mapstruct.config;

import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.mapstruct.FromToKey;
import com.dandandog.framework.mapstruct.IMapper;
import com.dandandog.framework.mapstruct.MapperUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class MapStructConfig {

    private final List<IMapper<?, ?>> iMappers;

    @Bean
    public MapperUtil mapperUtil() {
        Map<Class<?>, IMapper<?, ?>> bindings = Optional.ofNullable(iMappers).
                orElse(Lists.newArrayList()).stream()
                .collect(Collectors.toMap(iMapper -> ClassUtil.getTypeArgument(iMapper.getClass()), iMapper -> iMapper));
        return new MapperUtil(bindings);
    }


    private FromToKey createFromToKey(IMapper<?, ?> value) {
        ParameterizedType parameterized = (ParameterizedType) ((Class<?>) value.getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0];
        Class<?> fromClass = (Class<?>) parameterized.getActualTypeArguments()[0];
        Class<?> toClass = (Class<?>) parameterized.getActualTypeArguments()[1];
        return new FromToKey(fromClass, toClass);
    }

}
