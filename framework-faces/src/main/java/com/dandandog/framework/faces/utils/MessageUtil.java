package com.dandandog.framework.faces.utils;


import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.config.properties.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author JohnnyLiu
 */
@Component

@EnableConfigurationProperties(MessageProperties.class)
public class MessageUtil {


    private static MessageProperties properties;

    public MessageUtil(MessageProperties properties) {
        MessageUtil.properties = properties;
    }

    public static void addGlobalMessage(String title, String message, FacesMessage.Severity severity) {
        addGlobalMessage(new FacesMessage(severity, title, message));
    }

    public static void addGlobalMessage(FacesMessage facesMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        if (facesMessage.getSeverity().equals(FacesMessage.SEVERITY_ERROR)) {
            context.validationFailed();
        }
    }

    public static void showMessageGrowl(String title, String message, FacesMessage.Severity severity) {
        addGlobalMessage(title, message, severity);
        showMessageGrowl();
    }

    public static void showMessageDialog(String title, String message, FacesMessage.Severity severity) {
        addGlobalMessage(title, message, severity);
        showMessageDialog();
    }

    private static void showMessageGrowl() {
        RequestContextUtil.update(properties.getGrowlWidgetVar());
    }

    private static void showMessageDialog() {
        RequestContextUtil.executeAndUpdate(StrUtil.addSuffixIfNot(properties.getDialogWidgetVar(), ".show()"), "messages");
    }
}
