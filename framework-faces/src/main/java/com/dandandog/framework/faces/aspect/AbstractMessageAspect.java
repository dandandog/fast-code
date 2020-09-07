package com.dandandog.framework.faces.aspect;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.annotation.MessageRequired;
import com.dandandog.framework.faces.exception.MessageResolvableException;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JohnnyLiu
 */

public abstract class AbstractMessageAspect {


    private final MessageSource messageSource;

    public AbstractMessageAspect(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Setter
    private String messageCodePrefix = "framework.";

    @Around("@annotation(messageRequired)")
    public Object showMessage(ProceedingJoinPoint joinPoint, MessageRequired messageRequired) throws Throwable {
        String title = getMessageDetail(messageCodePrefix + "tipsMessages");
        StringBuilder messageCode = new StringBuilder(messageCodePrefix);
        String messageDetail;

        try {
            Object result = joinPoint.proceed();
            messageCode.append(messageRequired.type().getSuccessCode());
            messageDetail = getMessageDetail(messageCode.toString(), result);
            createFacesMessage(FacesMessage.SEVERITY_INFO, title, messageDetail);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            if ((e instanceof MessageResolvableException)) {
                MessageResolvableException e1 = (MessageResolvableException) e;
                messageDetail = getMessageDetail(e1.getCategory() + "." + e1.getErrorCode(), e1.getParameters());
            } else {
                messageCode.append(messageRequired.type().getFailedCode());
                String detail = getMessageDetail(messageCodePrefix + "contactTheAdmin");
                messageDetail = getMessageDetail(messageCode.toString(), detail);
            }
            createFacesMessage(FacesMessage.SEVERITY_ERROR, title, messageDetail);

        } finally {
            if (messageRequired.growl()) {
                renderMessages();
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

    private void createFacesMessage(FacesMessage.Severity severity, String title, String messageDetail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, title, messageDetail));
    }

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    protected abstract void renderMessages();
}
