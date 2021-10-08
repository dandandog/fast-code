package com.dandandog.framework.faces.event;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.LocaleResolver;

public class LocalePhaseListener implements PhaseListener {

    private static final long serialVersionUID = -5921990764355211584L;

    public static final String LOCALE_RESOLVER_BEAN_NAME = "localeResolver";

    public void beforePhase(PhaseEvent event) {
        HttpServletRequest request = getRequest(event.getFacesContext());
        LocaleResolver localeResolver = getLocaleResolver(event.getFacesContext());
        Locale locale = (localeResolver != null) ? localeResolver.resolveLocale(request) : request.getLocale();

        HttpServletResponse response = getResponse(event.getFacesContext());
        response.setLocale(locale);
    }

    public void afterPhase(PhaseEvent event) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return ((HttpServletRequest) context.getExternalContext().getRequest());
    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return ((HttpServletResponse) context.getExternalContext().getResponse());
    }

    private LocaleResolver getLocaleResolver(FacesContext context) {
        try {
            ApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(context);
            return applicationContext.getBean(LOCALE_RESOLVER_BEAN_NAME, LocaleResolver.class);
        } catch (NoSuchBeanDefinitionException e) {
        }
        return null;
    }

}
