package com.dandandog.framework.faces.utils;


import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.config.properties.MessageProperties;
import com.dandandog.framework.faces.controller.FacesController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JohnnyLiu
 */
@Component
@EnableConfigurationProperties(MessageProperties.class)
public class MessageUtil {

    private static MessageSource messageSource;

    private static MessageProperties properties;

    @Autowired
    public MessageUtil(MessageSource messageSource, MessageProperties properties) {
        MessageUtil.messageSource = messageSource;
        MessageUtil.properties = properties;
    }

    public static void addFacesMessage(String clientId, String title, String message, FacesMessage.Severity severity) {
        addFacesMessage(clientId, new FacesMessage(severity, title, message));
    }

    public static void addFacesMessage(String clientId, FacesMessage facesMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(clientId, facesMessage);
        if (facesMessage.getSeverity().equals(FacesMessage.SEVERITY_ERROR)) {
            context.validationFailed();
        }
    }

    public static String getMessageSource(String code, Object... detail) {
        return getMessageSource(null, code, detail);
    }

    public static String getMessageSource(String prefix, String code, Object... detail) {
        prefix = StrUtil.emptyToDefault(prefix, properties.getCodePrefix());
        prefix = StrUtil.addSuffixIfNot(prefix, ".");
        String msg = prefix + code;
        try {
            msg = messageSource.getMessage(msg, detail, getResponse().getLocale());
        } catch (NoSuchMessageException ignored) {
        }
        return msg;
    }

    private static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
}
