package com.dandandog.framework.faces.utils;


import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author JohnnyLiu
 */
@Component
public class MessageUtil {


    public static void addMessage(String clientId, String title, String message, FacesMessage.Severity severity) {
        addMessage(clientId, new FacesMessage(severity, title, message));
    }

    public static void addMessage(String clientId, FacesMessage facesMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(clientId, facesMessage);
        if (facesMessage.getSeverity().equals(FacesMessage.SEVERITY_ERROR)) {
            context.validationFailed();
        }
    }

//    public static void showMessageGrowl(String title, String message, FacesMessage.Severity severity) {
//        showMessageGrowl(properties.getDialogWidgetVar(), addMessage(title, message, severity));
//    }
//
//    public static void showMessageDialog(String title, String message, FacesMessage.Severity severity) {
//        showMessageGrowl("messages", addMessage(title, message, severity));
//    }
//
//    public static void showMessageDialog(FacesMessage facesMessage) {
//        PrimeFaces.current().dialog().showMessageDynamic(facesMessage);
//    }


}
