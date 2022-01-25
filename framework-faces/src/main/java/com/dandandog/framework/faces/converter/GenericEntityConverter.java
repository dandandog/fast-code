package com.dandandog.framework.faces.converter;


import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.common.model.IEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public abstract class GenericEntityConverter<T extends IEntity> implements Converter<T> {

    @Override
    public T getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (StrUtil.isEmpty(value))
            return null;
        T entity = this.getEntity(facesContext, component, value);
        if (entity != null)
            return entity;
        return notFoundEntityHandler(facesContext, component, value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, T t) {
        if (t == null)
            return "";
        if (t.getId() != null)
            return t.getId();
        return notFoundIdHandler(facesContext, component, t);
    }

    protected abstract T getEntity(FacesContext facesContext, UIComponent component, String value);

    protected String notFoundIdHandler(FacesContext facesContext, UIComponent component, T t) {
        throw new IllegalArgumentException("Entity has no id");
    }

    protected T notFoundEntityHandler(FacesContext facesContext, UIComponent component, String value) {
        throw new IllegalArgumentException("Entity not found");
    }


}
