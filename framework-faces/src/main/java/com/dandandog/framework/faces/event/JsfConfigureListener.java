package com.dandandog.framework.faces.event;

import com.dandandog.framework.faces.el.EmptyToNullStringELResolver;
import com.dandandog.framework.faces.el.FacesControllerELResolver;
import com.dandandog.framework.faces.el.PlatformMessageSourceELResolver;
import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.processor.FactoryConfigProcessor;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerELResolver;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.search.SearchExpressionContextFactory;
import javax.faces.component.visit.VisitContextFactory;
import javax.faces.context.ExternalContextFactory;
import javax.faces.context.FacesContextFactory;
import javax.faces.context.FlashFactory;
import javax.faces.context.PartialViewContextFactory;
import javax.faces.flow.FlowHandlerFactory;
import javax.faces.lifecycle.ClientWindowFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKitFactory;
import javax.faces.view.ViewDeclarationLanguageFactory;
import javax.faces.view.facelets.FaceletCacheFactory;
import javax.faces.view.facelets.TagHandlerDelegateFactory;
import javax.servlet.ServletContextEvent;
import java.util.Locale;

import static javax.faces.FactoryFinder.*;

/**
 * @author JohnnyLiu
 */
public class JsfConfigureListener extends ConfigureListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {


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

        super.contextInitialized(sce);

    }

}
