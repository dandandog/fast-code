package com.dandandog.framework.faces.event;

import org.primefaces.component.api.UICalendar;
import org.primefaces.extensions.component.layout.Layout;

import javax.faces.application.Application;
import javax.faces.application.ApplicationWrapper;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/10/8 16:57
 */
public class FacesApplication extends ApplicationWrapper {
    private static final Set<String> BLACKLISTED_COMPONENT_TYPES = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList(Layout.COMPONENT_TYPE)));

    private final Application wrapped;

    public FacesApplication(Application wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Application getWrapped() {
        return wrapped;
    }

    @Override
    public UIComponent createComponent(FacesContext context, String componentType, String rendererType) {
        // prevent certain components from being used
        if (BLACKLISTED_COMPONENT_TYPES.contains(componentType)) {
            throw new IllegalArgumentException(
                    componentType + " is deprecated and you should not be using this component.");
        }

        final UIComponent component = super.createComponent(context, componentType, rendererType);

        // set a global date pattern by default
        if (component instanceof UICalendar) {
            final UICalendar calendar = (UICalendar) component;
            calendar.setPattern("dd-MMM-yyyy");
        }

        return component;
    }

}
