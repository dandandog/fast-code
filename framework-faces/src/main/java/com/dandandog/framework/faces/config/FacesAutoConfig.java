package com.dandandog.framework.faces.config;

import com.dandandog.framework.faces.event.JsfConfigureListener;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Configuration

public class FacesAutoConfig {


    @Bean
    public ServletListenerRegistrationBean<JsfConfigureListener> jsfConfigureListenerBean() {
        return new ServletListenerRegistrationBean<>(new JsfConfigureListener());
    }

    @Bean
    public FilterRegistrationBean<FileUploadFilter> fileUploadFilter() {
        log.debug("~~~~~FileUploadFilter");
        FilterRegistrationBean<FileUploadFilter> filterBean = new FilterRegistrationBean<>(
                new FileUploadFilter());
        Map<String, String> initParameters = new HashMap<>(2);
        initParameters.put("thresholdSize", "5242880");
        initParameters.put("encoding", "UTF-8");
        filterBean.setInitParameters(initParameters);
        filterBean.setName("PrimeFaces FileUpload Filter");
        filterBean.addServletNames("Faces Servlet");
        return filterBean;
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter() {
        log.debug("~~~~~CharacterEncodingFilter");
        FilterRegistrationBean<CharacterEncodingFilter> filterBean = new FilterRegistrationBean<>(
                new CharacterEncodingFilter());
        filterBean.setName("Character Encoding Filter");
        Map<String, String> initParameters = new HashMap<>(2);
        initParameters.put("encoding", "UTF-8");
        initParameters.put("forceEncoding", "true");
        filterBean.setInitParameters(initParameters);
        return filterBean;
    }

}
