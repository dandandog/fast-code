package com.dandandog.framework.faces.aspect;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.annotation.MessageRequired;
import com.dandandog.framework.faces.config.properties.MessageProperties;
import com.dandandog.framework.faces.exception.MessageResolvableException;
import com.dandandog.framework.faces.utils.MessageUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.primefaces.PrimeFaces;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JohnnyLiu
 */
@AllArgsConstructor
@EnableConfigurationProperties(MessageProperties.class)
public abstract class AbstractMessageAspect {

    private final MessageSource messageSource;

    private final MessageProperties properties;

    @Around("@annotation(messageRequired)")
    public Object showMessage(ProceedingJoinPoint joinPoint, MessageRequired messageRequired) throws Throwable {
        String messageCodePrefix = properties.getCodePrefix();
        String title = getMessageDetail(messageCodePrefix + properties.getTitle());
        StringBuilder messageCode = new StringBuilder(messageCodePrefix);
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        String messageDetail = "null";
        try {
            Object result = joinPoint.proceed();
            messageCode.append(messageRequired.type().getSuccessCode());
            messageDetail = getMessageDetail(messageCode.toString(), result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            severity = FacesMessage.SEVERITY_ERROR;
            if ((e instanceof MessageResolvableException)) {
                MessageResolvableException e1 = (MessageResolvableException) e;
                messageDetail = getMessageDetail(e1.getCategory() + "." + e1.getErrorCode(), e1.getParameters());
            } else {
                messageCode.append(messageRequired.type().getFailedCode());
                String detail = getMessageDetail(messageCodePrefix + "contactTheAdmin");
                messageDetail = getMessageDetail(messageCode.toString(), detail);
            }
        } finally {
            MessageUtil.addMessage(messageRequired.notice().getClientId(), title, messageDetail, severity);
            switch (messageRequired.notice()) {
                case ALL:
                    updateAll();
                    break;
                case GROWL:
                    updateGrowl();
                    break;
                case MESSAGES:
                    updateMessages();
                    break;
            }
        }
        return null;
    }

    private String getMessageDetail(String messageCode, Object... detail) {
        String msg = null;
        try {
            msg = this.messageSource.getMessage(messageCode, detail, getResponse().getLocale());
        } catch (NoSuchMessageException ignored) {
        } finally {
            if (StrUtil.isBlank(msg)) {
                msg = messageCode;
            }
        }
        return msg;
    }


    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    protected abstract void updateAll();

    protected abstract void updateGrowl();

    protected abstract void updateMessages();
}
