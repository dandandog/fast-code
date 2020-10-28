package com.dandandog.framework.mapstruct;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import com.dandandog.framework.mapstruct.model.MapperVo;
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

    public static <F, T extends MapperVo<F>> T map(F from) {
        IMapper<F, T> mapper = hasMapper(from.getClass());
        return mapper.mapTo(from);
    }

    public static <F, T extends MapperVo<F>> F map(T to) {
        Class<?> fClass = ClassUtil.getTypeArgument(to.getClass());
        IMapper<F, T> mapper = hasMapper(fClass);
        return mapper.mapFrom(to);
    }

    public static <F, T extends MapperVo<F>> Collection<T> mapVoAll(List<F> fromList) {
        Class<?> fClass = CollUtil.getElementType(fromList);
        IMapper<F, T> mapper = hasMapper(fClass);
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    public static <F, T extends MapperVo<F>> Collection<F> mapEntityAll(List<T> fromList) {
        Class<?> tClass = CollUtil.getElementType(fromList);
        Class<?> fClass = ClassUtil.getTypeArgument(tClass);
        IMapper<F, T> mapper = hasMapper(fClass);
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(mapper::mapFrom).collect(Collectors.toList());
    }


}
