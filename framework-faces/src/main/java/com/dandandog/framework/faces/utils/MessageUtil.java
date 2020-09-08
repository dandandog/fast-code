package com.dandandog.framework.faces.utils;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public class MessageUtil {

    public static void showSuccessMessage(String message, FacesMessage.Severity severity) {
        showMessageGrowl(message, severity);
    }

    public static void showDialogMessage(String message, FacesMessage.Severity severity) {
        showMessageDialog(message, severity);
    }

    public static void addGlobalMessage(String message, FacesMessage.Severity severity) {
        addGlobalMessage(new FacesMessage(severity, message, message));
    }

    public static void addGlobalMessage(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void addGlobalMessages(List<String> messages, FacesMessage.Severity severity) {
        for (String message : messages) {
            addGlobalMessage(message, severity);
        }
    }

    public static void showMessageGrowl() {
        RequestContextUtil.update("globalMessageGrowl");
    }

    public static void showMessageDialog() {
        RequestContextUtil.executeAndUpdate("globalMessageDialog.show();", "globalMessages");
    }

    private static void showMessageGrowl(String message, FacesMessage.Severity severity) {
        addGlobalMessage(message, severity);
        showMessageGrowl();
    }

    private static void showMessageDialog(String message, FacesMessage.Severity severity) {
        addGlobalMessage(message, severity);
        showMessageDialog();
    }
}
