package com.dandandog.framework.faces.event;

import com.apj.framework.web.faces.el.FacesControllerELResolver;
import com.apj.framework.web.faces.el.PlatformMessageSourceELResolver;
import com.dandandog.framework.faces.el.FacesControllerELResolver;
import com.sun.faces.config.ConfigureListener;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContextEvent;
import java.util.Locale;

/**
 * @author create by Johnny
 * @description com.johnny.web.faces.el
 * @date 七月 06,2019
 */
public class JsfConfigureListener extends ConfigureListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);

		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		Application app = appFactory.getApplication();
		app.addELResolver(new SpringBeanFacesELResolver());
		app.addELResolver(new FacesControllerELResolver());
		app.addELResolver(new PlatformMessageSourceELResolver());
		app.setDefaultLocale(new Locale("zh_CN"));

		// Lifecycle
		LifecycleFactory lifeFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		Lifecycle lifecycle = lifeFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
		lifecycle.addPhaseListener(new ControllerPhaseListener());

	}

}
