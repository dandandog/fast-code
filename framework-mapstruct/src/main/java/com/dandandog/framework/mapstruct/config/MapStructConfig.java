package com.dandandog.framework.mapstruct.config;

import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.mapstruct.*;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
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
@SuppressWarnings({"rawtypes"})
public class MapStructConfig {


    private final Map<String, StandardMapper> resources;

    private final List<IMapper<?, ?>> iMappers;


    @Bean
    public MapperRepo mapperRepo() {
        return new MapperRepo(bindings(), listBindings());
    }

    @Bean
    public MapperUtil mapperUtil() {
        Map<Class<?>, IMapper<?, ?>> bindings = iMappers.stream().collect(Collectors.toMap(iMapper -> ClassUtil.getTypeArgument(iMapper.getClass()), iMapper -> iMapper));
        return new MapperUtil(bindings);
    }


    private Map<FromToKey, StandardMapper> bindings() {
        return Optional.ofNullable(resources).map(res -> res.values().stream().collect(
                Collectors.toMap(this::createFromToKey, standardMapper -> standardMapper))).orElse(Maps.newHashMap());
    }

    private Multimap<Class<?>, StandardMapper> listBindings() {
        Multimap<Class<?>, StandardMapper> bindings = LinkedListMultimap.create();
        Optional.ofNullable(resources).orElse(Maps.newHashMap()).forEach((key, value) -> {
            FromToKey fromToKey = createFromToKey(value);
            bindings.put(fromToKey.getFrom(), value);
            bindings.put(fromToKey.getTo(), value);
        });
        return bindings;
    }

    private FromToKey createFromToKey(StandardMapper<?, ?> value) {
        ParameterizedType parameterized = (ParameterizedType) ((Class<?>) value.getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0];
        Class<?> fromClass = (Class<?>) parameterized.getActualTypeArguments()[0];
        Class<?> toClass = (Class<?>) parameterized.getActualTypeArguments()[1];
        return new FromToKey(fromClass, toClass);
    }
}
