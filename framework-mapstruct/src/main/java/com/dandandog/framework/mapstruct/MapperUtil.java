package com.dandandog.framework.mapstruct;

import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.mapstruct.model.MapperEntity;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
public final class MapperUtil {

    private static Map<Class<?>, IMapper<?, ?>> bindings;

    public MapperUtil(Map<Class<?>, IMapper<?, ?>> bindings) {
        MapperUtil.bindings = bindings;
    }

    public static IMapper hasMapper(Class<?> fClass) {
        return bindings.get(fClass);
    }

    public static <F, T extends MapperEntity<F>> T map(F from) {
        IMapper<F, T> mapper = hasMapper(from.getClass());
        return mapper.mapTo(from);
    }

    public static <F, T extends MapperEntity<F>> F map(T to) {
        Class<?> fClass = ClassUtil.getTypeArgument(to.getClass());
        IMapper<F, T> mapper = hasMapper(fClass);
        return mapper.mapFrom(to);
    }

    public static <F, T extends MapperEntity<F>> Collection<T> mapToAll(List<F> fromList) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(f -> {
            Class<?> fClass = f.getClass();
            IMapper<F, T> mapper = hasMapper(fClass);
            return mapper.mapTo(f);
        }).collect(Collectors.toList());
    }

    public static <F, T extends MapperEntity<F>> Collection<F> mapFromAll(List<T> fromList) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(t -> {
            Class<?> tClass = t.getClass();
            Class<?> fClass = ClassUtil.getTypeArgument(tClass);
            IMapper<F, T> mapper = hasMapper(fClass);
            return mapper.mapFrom(t);
        }).collect(Collectors.toList());
    }


}
