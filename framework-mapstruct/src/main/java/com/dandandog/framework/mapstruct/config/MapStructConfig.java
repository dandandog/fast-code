package com.dandandog.framework.mapstruct.config;

import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.mapstruct.FromToKey;
import com.dandandog.framework.mapstruct.IMapper;
import com.dandandog.framework.mapstruct.MapperUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        Map<FromToKey, IMapper<?, ?>> bindings = Optional.ofNullable(iMappers).
                orElse(Lists.newArrayList()).stream()
                .collect(Collectors.toMap(this::createFromToKey, iMapper -> iMapper));
        return new MapperUtil(bindings);
    }


    private FromToKey createFromToKey(IMapper<?, ?> value) {
        Class<?> fromClass = ClassUtil.getTypeArgument(value.getClass(), 0);
        Class<?> toClass = ClassUtil.getTypeArgument(value.getClass(), 1);
        return new FromToKey(fromClass, toClass);
    }

}
