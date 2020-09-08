package com.dandandog.framework.faces.el;

import com.dandandog.framework.faces.controller.FacesController;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;
import java.beans.FeatureDescriptor;
import java.util.Iterator;

/**
 * @author JohnnyLiu
 */
public class FacesControllerELResolver extends ELResolver {
    static final String FACES_CONTROLLER_KEY = "controller";

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return FacesController.class;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        if ((base == null) && (FACES_CONTROLLER_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return FacesController.class;
        }
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        if ((base == null) && (FACES_CONTROLLER_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return FacesController.getCurrentInstance();
        }
        return null;
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        if ((base == null) && (FACES_CONTROLLER_KEY.equals(property))) {
            context.setPropertyResolved(true);
            return true;
        }
        return false;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
        if ((base == null) && (FACES_CONTROLLER_KEY.equals(property))) {
            throw new PropertyNotWritableException("The 'controller' variable is not writable.");
        }
    }
}
