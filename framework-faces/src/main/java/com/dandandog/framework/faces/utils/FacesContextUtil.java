package com.dandandog.framework.faces.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * @author create by Johnny
 * @description com.johnny.web.restful
 * @date 七月 06,2019
 */
public class FacesContextUtil {

    public static ApplicationContext getApplicationContext() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public static <T> T getBean(Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(name, clazz);
    }

}
