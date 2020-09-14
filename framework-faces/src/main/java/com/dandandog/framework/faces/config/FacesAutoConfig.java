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
public class FacesAutoConfig implements ServletContextAware {



    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
        servletContext.setInitParameter("javax.faces.validator.ENABLE_VALIDATE_WHOLE_BEAN", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", Boolean.TRUE.toString());

        servletContext.setInitParameter("primefaces.TRANSFORM_METADATA", Boolean.TRUE.toString());
        servletContext.setInitParameter("primefaces.THEME", "omega");
        servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
        servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
        servletContext.setInitParameter("primefaces.UPLOADER", "auto");
    }


    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServletBean() {
        ServletRegistrationBean<FacesServlet> servletBean = new ServletRegistrationBean<>(facesServlet(),
                "*.faces");
        servletBean.setLoadOnStartup(1);
        servletBean.setName("Faces Servlet");
        return servletBean;
    }

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

//    @Bean
//    public FilterRegistrationBean<UrlRewriteFilter> urlRewrite() {
//        log.debug("~~~~~UrlRewriteFilter");
//        UrlRewriteFilter rewriteFilter = new UrlRewriteFilter();
//        FilterRegistrationBean<UrlRewriteFilter> registration = new FilterRegistrationBean<>(rewriteFilter);
//        registration.setUrlPatterns(Collections.singletonList("/*"));
//        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        Map<String, String> initParam = new HashMap<>();
//        initParam.put("confPath", "urlrewrite.xml");
//        initParam.put("logLevel", "DEBUG");
//
//        registration.setInitParameters(initParam);
//        return registration;
//    }

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
