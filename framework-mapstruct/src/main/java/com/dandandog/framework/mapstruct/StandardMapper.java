/**
 *
 */
package com.dandandog.framework.mapstruct;

import com.dandandog.framework.mapstruct.context.BaseContext;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

/**
 * @author jucheme
 *
 */
public interface StandardMapper<From, To> {

    /**
     * Maps from one object to another.
     *
     * @param from 原对象
     * @return to
     */
    To mapTo(From from);

    /**
     * Maps from one object to another.
     *
     * @param from 原对象
     * @param context 填充内容
     * @return 映射对象
     */
    To mapTo(From from, @Context BaseContext context);

    /**
     * Maps to one object from another.
     *
     * @param to 映射对象
     * @return from 原对象
     */
    From mapFrom(To to);

    /**
     * Maps to one object from another.
     *
     * @param to 映射对象
     * @param context BaseContext
     * @return from 原对象
     */
    From mapFrom(To to, @Context BaseContext context);

    /**
     * Update the object.
     *
     * @param from 原对象
     * @param to 映射对象
     * @return 映射对象
     */
    To updateTo(From from, @MappingTarget To to);


    /**
     * Update the object.
     *
     * @param from 原对象
     * @param to 映射对象
     * @return 映射对象
     */
    From updateFrom(To to, @MappingTarget From from);


}
