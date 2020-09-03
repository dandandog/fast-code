package com.dandandog.framework.mapstruct;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.mapstruct.context.BaseContext;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author JohnnyLiu
 */
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
public class MapperRepo {

    private static Map<FromToKey, StandardMapper> bindings;

    private static Multimap<Class<?>, StandardMapper> listBindings;


    public MapperRepo(Map<FromToKey, StandardMapper> bindings, Multimap<Class<?>, StandardMapper> listBindings) {
        MapperRepo.bindings = bindings;
        MapperRepo.listBindings = listBindings;
        log.debug("MapperRepo FromToKey : {}", Joiner.on("\r\n").join(bindings.keySet()));
    }

    protected static StandardMapper hasMapper(FromToKey fromToKey) {
        StandardMapper mapper = bindings.get(fromToKey);
        return Optional.ofNullable(mapper).orElseThrow(() -> new RuntimeException(StrUtil.format("FromToKey {} 不存在", fromToKey)));
    }

    public static <F, T> T mapTo(F from, Class<T> to) {
        return mapTo(from, to, null);
    }

    public static <F, T> Collection<T> mapTo(Collection<F> from, Class<T> to) {
        return mapTo(from, to, null);
    }


    public static <F, T> F mapFrom(T to, Class<F> from) {
        return mapFrom(to, from, null);
    }

    public static <F, T> Collection<F> mapFrom(Collection<T> to, Class<F> from) {
        return mapFrom(to, from, null);
    }

    public static <F, T> T updateTo(F from, T to) {
        StandardMapper<F, T> mapper = hasMapper(new FromToKey(from.getClass(), to.getClass()));
        return mapper.updateTo(from, to);
    }

    public static <F, T> F updateFrom(T to, F from) {
        StandardMapper<F, T> mapper = hasMapper(new FromToKey(from.getClass(), to.getClass()));
        return mapper.updateFrom(to, from);
    }

    public static <F> List<Object> mapAllTo(F from) {
        List<Object> mappings = new ArrayList<>();
        if (listBindings.containsKey(from.getClass())) {
            listBindings.get(from.getClass()).forEach(mapper -> mappings.add(mapper.mapTo(from)));
        }
        return mappings;
    }

    public static <T> List<Object> mapAllFrom(T to) {
        List<Object> mappings = new ArrayList<>();
        if (listBindings.containsKey(to.getClass())) {
            listBindings.get(to.getClass()).forEach(mapper -> mappings.add(mapper.mapFrom(to)));
        }
        return mappings;
    }

    public static <F, T> T merge(Class<T> to, F... merges) {
        T t = null;
        for (F from : merges) {
            StandardMapper<F, T> mapper = hasMapper(new FromToKey(from.getClass(), to));
            t = t == null ? mapper.mapTo(from) : mapper.updateTo(from, t);
        }
        return t;
    }

    public static boolean hasRegisteredMapper(Class from, Class to) {
        return bindings.containsKey(new FromToKey(from, to));
    }


    public static <F, T> T mapTo(F from, Class<T> to, BaseContext context) {
        StandardMapper<F, T> mapper = hasMapper(new FromToKey(from.getClass(), to));
        return Optional.ofNullable(context).map(e -> mapper.mapTo(from, context)).orElseGet(() -> mapper.mapTo(from));
    }

    public static <F, T> Collection<T> mapTo(Collection<F> from, Class<T> to, BaseContext context) {
        if (!CollectionUtil.isEmpty(from)) {
            return from.stream().map(f -> mapTo(f, to, context)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    public static <F, T> F mapFrom(T to, Class<F> from, BaseContext context) {
        StandardMapper<F, T> mapper = hasMapper(new FromToKey(from, to.getClass()));
        return Optional.ofNullable(context).map(e -> mapper.mapFrom(to, context)).orElseGet(() -> mapper.mapFrom(to));
    }

    public static <F, T> Collection<F> mapFrom(Collection<T> to, Class<F> from, BaseContext context) {
        if (!CollectionUtil.isEmpty(to)) {
            return to.stream().map(t -> mapFrom(t, from, context)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }


}
