package com.dandandog.framework.faces.aspect;

import com.dandandog.framework.faces.exception.MessageResolvableException;
import com.dandandog.framework.faces.exception.UnifiedException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.primefaces.PrimeFaces;
import org.primefaces.clientwindow.PrimeClientWindowLifecycleFactory;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/11/11 16:42
 */
//@Aspect
//@Component
public class ControllerAspect {

    @Pointcut("target(com.dandandog.framework.faces.controller.FacesController) && execution(public void *(..)) ")
    public void point() {
    }

    @Before("point()")
    public void before() {
        System.out.println("before ...");
    }

    @AfterThrowing(value = "point()", throwing = "ex")
    public void unifiedExceptionHandler(Exception ex) {
        if (!(ex instanceof MessageResolvableException)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "What we do in life", "Echoes in eternity."));
        }
    }
}
