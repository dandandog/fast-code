package com.dandandog.framework.mapstruct;

import com.dandandog.framework.mapstruct.context.BaseContext;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

/**
 * @author JohnnyLiu
 */
public interface IMapper<From, To> {


    /**
     * Maps from one object to another.
     *
     * @param from 原对象
     * @return to
     */
    To mapTo(From from);


    /**
     * Maps to one object from another.
     *
     * @param to 映射对象
     * @return from 原对象
     */
    @InheritInverseConfiguration(name = "mapTo")
    From mapFrom(To to);


    /**
     * Maps from one object to another.
     *
     * @param from    原对象
     * @param context 填充内容
     * @return 映射对象
     */

    To mapContextTo(From from, @Context BaseContext<To> context);


    /**
     * Maps to one object from another.
     *
     * @param to      映射对象
     * @param context BaseContext
     * @return from 原对象
     */
    @InheritInverseConfiguration(name = "mapContextTo")
    From mapContextFrom(To to, @Context BaseContext<From> context);

    /**
     * Update the object.
     *
     * @param from 原对象
     * @param to   映射对象
     * @return 映射对象
     */
    To updateTo(From from, @MappingTarget To to);


    /**
     * Update the object.
     *
     * @param from 原对象
     * @param to   映射对象
     * @return 映射对象
     */
    @InheritInverseConfiguration(name = "updateTo")
    From updateFrom(To to, @MappingTarget From from);
}
