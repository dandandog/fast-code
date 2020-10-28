package com.dandandog.framework.mapstruct;

import com.dandandog.framework.mapstruct.model.MapperVo;

import java.util.Map;

/**
 * @author JohnnyLiu
 */
public final class MapperUtil {

    private static Map<Class<?>, IMapper> bindings;


    public MapperUtil(Map<Class<?>, IMapper> bindings) {
        this.bindings = bindings;
    }


    public static <T> IMapper hasMapper(Class<T> fClass) {
        return bindings.get(fClass);
    }

    public static <F, T extends MapperVo<F>> T mapTo(F f) {
        IMapper<F, T> mapper = hasMapper(f.getClass());
        return mapper.mapTo(f);
    }

}
