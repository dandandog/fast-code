package com.dandandog.framework.faces.aspect;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.annotation.MessageRequired;
import com.dandandog.framework.faces.config.properties.MessageProperties;
import com.dandandog.framework.faces.exception.MessageResolvableException;
import com.dandandog.framework.faces.utils.MessageUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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

        String messageDetail;

        try {
            Object result = joinPoint.proceed();
            if (messageRequired.growl()) {
                messageCode.append(messageRequired.type().getSuccessCode());
                messageDetail = getMessageDetail(messageCode.toString(), result);
                MessageUtil.showMessageGrowl(title, messageDetail, FacesMessage.SEVERITY_INFO);
            }
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
            MessageUtil.showMessageDialog(title, messageDetail, FacesMessage.SEVERITY_ERROR);
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


    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    protected abstract void renderMessages();
}
