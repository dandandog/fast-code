package com.dandandog.framework.faces.converter;

import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericEnumConverter<T extends Enum<T>> implements Converter {
	protected Class<T> enumClass;

	public GenericEnumConverter() {
		this.enumClass = ((Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return Enum.valueOf(this.enumClass, value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

}