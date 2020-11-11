package com.dandandog.framework.mapstruct;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.common.exception.FrameworkException;
import com.dandandog.framework.common.model.IEntity;
import com.dandandog.framework.mapstruct.context.BaseContext;
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

    private static Map<FromToKey, IMapper<?, ?>> bindings;

    public MapperUtil(Map<FromToKey, IMapper<?, ?>> bindings) {
        MapperUtil.bindings = bindings;
    }

    protected static IMapper getMapper(FromToKey fromToKey) {
        return Optional.ofNullable(bindings.get(fromToKey)).orElseThrow(() ->
                new FrameworkException(StrUtil.format("FromToKey {} 不存在", fromToKey)));
    }

    public static <F, T> T mapTo(F from, Class<T> tClass) {
        IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), tClass));
        return mapper.mapTo(from);
    }

    public static <F, T> T mapTo(F from, Class<T> tClass, BaseContext baseContext) {
        IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), tClass));
        return mapper.mapContextTo(from, baseContext);
    }

    public static <F, T> F mapFrom(T to, Class<F> fClass) {
        IMapper<F, T> mapper = getMapper(new FromToKey(fClass, to.getClass()));
        return mapper.mapFrom(to);
    }

    public static <F, T> F mapFrom(T to, Class<F> fClass, BaseContext baseContext) {
        IMapper<F, T> mapper = getMapper(new FromToKey(fClass, to.getClass()));
        return mapper.mapContextFrom(to, baseContext);
    }

    public static <F extends IEntity, T extends MapperVo> T map(F from, Class<T> tClass) {
        IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), tClass));
        return mapper.mapTo(from);
    }

    public static <F extends IEntity, T extends MapperVo> T map(F from, Class<T> tClass, BaseContext baseContext) {
        IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), tClass));
        return mapper.mapContextTo(from, baseContext);
    }

    public static <F extends IEntity, T extends MapperVo> F map(T to, Class<F> fClass) {
        IMapper<F, T> mapper = getMapper(new FromToKey(fClass, to.getClass()));
        return mapper.mapFrom(to);
    }

    public static <F extends IEntity, T extends MapperVo> F map(T to, Class<F> fClass, BaseContext baseContext) {
        IMapper<F, T> mapper = getMapper(new FromToKey(fClass, to.getClass()));
        return mapper.mapContextFrom(to, baseContext);
    }

    public static <F extends IEntity, T extends MapperVo> Collection<T> mapToAll(List<F> fromList, Class<T> tClass) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(f -> map(f, tClass)).collect(Collectors.toList());
    }

    public static <F extends IEntity, T extends MapperVo> Collection<T> mapToAll(List<F> fromList, Class<T> tClass, BaseContext baseContext) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(f -> map(f, tClass, baseContext)).collect(Collectors.toList());
    }

    public static <F extends IEntity, T extends MapperVo> Collection<F> mapFromAll(List<T> toList, Class<F> fClass) {
        return Optional.ofNullable(toList).orElse(Lists.newArrayList()).stream().map(t -> map(t, fClass)).collect(Collectors.toList());
    }

    public static <F extends IEntity, T extends MapperVo> Collection<F> mapFromAll(List<T> toList, Class<F> fClass, BaseContext baseContext) {
        return Optional.ofNullable(toList).orElse(Lists.newArrayList()).stream().map(t -> map(t, fClass, baseContext)).collect(Collectors.toList());
    }

    public static <F, T> T merge(Class<T> to, F... merges) {
        T t = null;
        for (F from : merges) {
            IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), to));
            t = t == null ? mapper.mapTo(from) : mapper.updateTo(from, t);
        }
        return t;
    }

    public static <F, T> T merge(T t, F... merges) {
        for (F from : merges) {
            IMapper<F, T> mapper = getMapper(new FromToKey(from.getClass(), t.getClass()));
            t = mapper.updateTo(from, t);
        }
        return t;
    }

}
