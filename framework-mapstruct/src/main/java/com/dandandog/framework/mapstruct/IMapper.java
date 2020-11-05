package com.dandandog.framework.mapstruct;

import com.dandandog.framework.mapstruct.context.BaseContext;
import org.mapstruct.Context;

/**
 * @author JohnnyLiu
 */
public interface IMapper<F, T> {

    T mapTo(F f);

    T mapTo(F f, @Context BaseContext context);

    F mapFrom(T t);

    F mapFrom(T t, @Context BaseContext context);

}
