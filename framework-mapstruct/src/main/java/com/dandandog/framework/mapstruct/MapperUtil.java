package com.dandandog.framework.mapstruct;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.common.exception.FrameworkException;
import com.dandandog.framework.common.model.IEntity;
import com.dandandog.framework.mapstruct.context.BaseContext;
import com.dandandog.framework.mapstruct.model.MapperVo;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
public final class MapperUtil {

    private static Map<FromToKey, IMapper> bindings;

    public MapperUtil(Map<FromToKey, IMapper> bindings) {
        MapperUtil.bindings = bindings;
    }

    public static IMapper getMapper(FromToKey fromToKey) {
        return Optional.ofNullable(bindings.get(fromToKey)).orElseThrow(() ->
                new FrameworkException(StrUtil.format("FromToKey {} 不存在", fromToKey)));
    }

    public static <F, T> IMapper<F, T> getMapper(Class<?> fClass, Class<?> tClass) {
        return getMapper(new FromToKey(fClass, tClass));
    }

    public static <F, T> T mapTo(F from, Class<T> tClass) {
        IMapper<F, T> mapper = getMapper(from.getClass(), tClass);
        return mapper.mapTo(from);
    }

    public static <F, T> T mapTo(F from, Class<T> tClass, BaseContext<T> baseContext) {
        IMapper<F, T> mapper = getMapper(from.getClass(), tClass);
        return mapper.mapContextTo(from, baseContext);
    }

    public static <F, T> F mapFrom(T to, Class<F> fClass) {
        IMapper<F, T> mapper = getMapper(fClass, to.getClass());
        return mapper.mapFrom(to);
    }

    public static <F, T> F mapFrom(T to, Class<F> fClass, BaseContext<F> baseContext) {
        IMapper<F, T> mapper = getMapper(fClass, to.getClass());
        return mapper.mapContextFrom(to, baseContext);
    }

    public static <F extends IEntity, T extends MapperVo> T map(F from, Class<T> tClass) {
        IMapper<F, T> mapper = getMapper(from.getClass(), tClass);
        return mapper.mapTo(from);
    }

    public static <F extends IEntity, T extends MapperVo> T map(F from, Class<T> tClass, BaseContext<T> baseContext) {
        IMapper<F, T> mapper = getMapper(from.getClass(), tClass);
        return Optional.ofNullable(baseContext).map(context -> mapper.mapContextTo(from, context)).orElse(mapper.mapTo(from));
    }

    public static <F extends IEntity, T extends MapperVo> F map(T to, Class<F> fClass) {
        IMapper<F, T> mapper = getMapper(fClass, to.getClass());
        return mapper.mapFrom(to);
    }

    public static <F extends IEntity, T extends MapperVo> F map(T to, Class<F> fClass, BaseContext<F> baseContext) {
        IMapper<F, T> mapper = getMapper(fClass, to.getClass());
        return Optional.ofNullable(baseContext).map(context -> mapper.mapContextFrom(to, context)).orElse(mapper.mapFrom(to));
    }

    public static <F, T> Collection<T> mapToAll(Collection<F> fromList, Class<T> tClass) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(f -> mapTo(f, tClass)).collect(Collectors.toList());
    }

    public static <F, T> Collection<T> mapToAll(Collection<F> fromList, Class<T> tClass, BaseContext<T> baseContext) {
        return Optional.ofNullable(fromList).orElse(Lists.newArrayList()).stream().map(f -> mapTo(f, tClass, baseContext)).collect(Collectors.toList());
    }

    public static <F, T> Collection<F> mapFromAll(Collection<T> toList, Class<F> fClass) {
        return Optional.ofNullable(toList).orElse(Lists.newArrayList()).stream().map(t -> mapFrom(t, fClass)).collect(Collectors.toList());
    }

    public static <F, T> Collection<F> mapFromAll(Collection<T> toList, Class<F> fClass, BaseContext<F> baseContext) {
        return Optional.ofNullable(toList).orElse(Lists.newArrayList()).stream().map(t -> mapFrom(t, fClass, baseContext)).collect(Collectors.toList());
    }

    public static <F, T> T mergeTo(Class<T> to, F... merges) {
        T t = null;
        for (F from : merges) {
            if (from == null) {
                continue;
            }
            IMapper<F, T> mapper = getMapper(from.getClass(), to);
            t = t == null ? mapper.mapTo(from) : mapper.updateTo(from, t);
        }
        return t;
    }

    public static <F, T> T mergeTo(T t, F... merges) {
        for (F from : merges) {
            if (from == null) {
                continue;
            }
            IMapper<F, T> mapper = getMapper(from.getClass(), t.getClass());
            t = mapper.updateTo(from, t);
        }
        return t;
    }

    public static <F, T> F mergeFrom(Class<F> from, T... merges) {
        F f = null;
        for (T to : merges) {
            if (to == null) {
                continue;
            }
            IMapper<F, T> mapper = getMapper(new FromToKey(from, to.getClass()));
            f = f == null ? mapper.mapFrom(to) : mapper.updateFrom(to, f);
        }
        return f;
    }

    public static <F, T> F mergeFrom(F f, T... merges) {
        for (T to : merges) {
            if (to == null) {
                continue;
            }
            IMapper<F, T> mapper = getMapper(new FromToKey(f.getClass(), to.getClass()));
            f = mapper.updateFrom(to, f);
        }
        return f;
    }
}
