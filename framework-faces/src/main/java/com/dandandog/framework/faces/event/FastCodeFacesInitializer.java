package com.dandandog.framework.faces.event;

import com.dandandog.framework.faces.el.EmptyToNullStringELResolver;
import com.dandandog.framework.faces.el.FacesControllerELResolver;
import com.dandandog.framework.faces.el.PlatformMessageSourceELResolver;
import com.sun.faces.config.FacesInitializer;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerELResolver;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;
import org.primefaces.component.lifecycle.LifecyclePhaseListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Locale;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/11/12 18:32
 */
public class FastCodeFacesInitializer extends FacesInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext ctxt) throws ServletException {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        Application app = appFactory.getApplication();
        app.setMessageBundle("jsf-messages");
        app.addELResolver(new SpringBeanFacesELResolver());
        app.addELResolver(new FacesControllerELResolver());
        app.addELResolver(new PlatformMessageSourceELResolver());
        app.addELResolver(new EmptyToNullStringELResolver());
        app.addELResolver(new PrimeExceptionHandlerELResolver());
        app.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);


        // Lifecycle
        LifecycleFactory lifeFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifeFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        lifecycle.addPhaseListener(new ControllerPhaseListener());
        lifecycle.addPhaseListener(new LocalePhaseListener());
        lifecycle.addPhaseListener(new LifecyclePhaseListener());

        FactoryFinder.setFactory(FactoryFinder.EXCEPTION_HANDLER_FACTORY, PrimeExceptionHandlerFactory.class.getName());
    }


}
