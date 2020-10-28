package com.dandandog.framework.mapstruct;

import com.dandandog.framework.mapstruct.context.BaseContext;
import com.dandandog.framework.mapstruct.model.MapperVo;
import org.mapstruct.Context;

/**
 * @author JohnnyLiu
 */
public interface IMapper<F, T extends MapperVo<F>> {

    T mapTo(F f);

    T mapTo(F f, @Context BaseContext context);

    F mapFrom(T t);

    F mapFrom(T t, @Context BaseContext context);

}
