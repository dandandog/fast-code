package com.dandandog.framework.mapstruct.config;

import com.dandandog.framework.mapstruct.FromToKey;
import com.dandandog.framework.mapstruct.MapperRepo;
import com.dandandog.framework.mapstruct.StandardMapper;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
@Slf4j
@AllArgsConstructor
@ComponentScan("com.dandandog.framework.mapstruct")
@SuppressWarnings({ "rawtypes"})
public class MapStructConfig {

    private final Map<String, StandardMapper> resources;

    @Bean
    public MapperRepo mapperRepo() {
        return new MapperRepo(bindings(), listBindings());
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
