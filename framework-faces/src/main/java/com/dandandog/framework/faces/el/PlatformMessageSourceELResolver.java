package com.dandandog.framework.faces.el;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.context.MessageSource;
import org.springframework.web.jsf.FacesContextUtils;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public class PlatformMessageSourceELResolver extends ELResolver {

    private static final String RESOURCE_BUNDLE_KEY = "msg";

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        if (base == null) {
            return MessageSource.class;
        }
        if ((base instanceof MessageSource)) {
            return SystemMessageSource.class;
        }
        if ((base instanceof SystemMessageSource)) {
            return String.class;
        }
        return null;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        if ((base == null) && (RESOURCE_BUNDLE_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return MessageSource.class;
        }
        if ((base instanceof MessageSource)) {
            context.setPropertyResolved(true);
            return SystemMessageSource.class;
        }
        if ((base instanceof SystemMessageSource)) {
            context.setPropertyResolved(true);
            return String.class;
        }
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        if ((base == null) && (RESOURCE_BUNDLE_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return getMessageSource();
        }
        if ((base instanceof MessageSource)) {
            context.setPropertyResolved(true);
            return new SystemMessageSource((MessageSource) base, property.toString());
        }
        if ((base instanceof SystemMessageSource)) {
            context.setPropertyResolved(true);
            SystemMessageSource systemMessageSource = (SystemMessageSource) base;
            MessageSource messageSource = systemMessageSource.getMessageSource();
            String systemName = systemMessageSource.getSystemName();
            String code = getCode(systemName, property);
            Object[] params = getParams(property);
            String message = messageSource.getMessage(code, params, code, getResponse().getLocale());
            if (message != null) {
                return message;
            }
            return property.toString();
        }
        return null;
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        if ((base == null) && (RESOURCE_BUNDLE_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return true;
        }
        if ((base instanceof MessageSource)) {
            context.setPropertyResolved(true);
            return true;
        }
        if ((base instanceof SystemMessageSource)) {
            context.setPropertyResolved(true);
            return true;
        }
        return false;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
        if ((base == null) && (RESOURCE_BUNDLE_KEY.equals(property))) {
            throw new PropertyNotWritableException("The 'msg' variable is not writable.");
        }
        if ((base instanceof MessageSource)) {
            throw new PropertyNotWritableException("The MessageSource is not writable.");
        }
        if ((base instanceof SystemMessageSource)) {
            throw new PropertyNotWritableException("The SystemMessageSource is not writable.");
        }
    }

    protected MessageSource getMessageSource() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return FacesContextUtils.getRequiredWebApplicationContext(facesContext);
    }

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    private String getCode(String systemName, Object property) {
        if (property instanceof List) {
            List list = (List) property;
            return systemName + "." + list.get(0).toString();
        }
        return systemName + "." + property.toString();
    }

    private Object[] getParams(Object property) {
        if (property instanceof List) {
            List list = (List) property;
            if (list.get(1) instanceof List) {
                List paramList = (List) list.get(1);
                return ArrayUtil.toArray(paramList, Object.class);
            }
            return new Object[] {list.get(1)};
        }
        return null;
    }
}
