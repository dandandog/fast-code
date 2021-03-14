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
        String title = getMessageDetail(properties.getTitle());
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        String messageDetail;
        try {
            Object result = joinPoint.proceed();
            messageDetail = getMessageDetail(messageRequired.type().getSuccessCode(), result);
            MessageUtil.addMessage(title, messageDetail, severity);
            successShow();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            severity = FacesMessage.SEVERITY_ERROR;
            if ((e instanceof MessageResolvableException)) {
                MessageResolvableException e1 = (MessageResolvableException) e;
                messageDetail = getMessageDetail(e1.getCategory(), e1.getErrorCode(), e1.getParameters());
                MessageUtil.addMessage(title, messageDetail, severity);
                resolvableShow();
            } else {
                String detail = getMessageDetail("contactTheAdmin");
                messageDetail = getMessageDetail(messageRequired.type().getFailedCode(), detail);
                MessageUtil.addMessage(title, messageDetail, severity);
                errorShow();
            }
        }
        return null;
    }

    private String getMessageDetail(String code, Object... detail) {
        return getMessageDetail(null, code, detail);
    }

    private String getMessageDetail(String prefix, String code, Object... detail) {
        prefix = StrUtil.emptyToDefault(prefix, properties.getCodePrefix());
        String msg = prefix + code;
        try {
            msg = this.messageSource.getMessage(msg, detail, getResponse().getLocale());
        } catch (NoSuchMessageException ignored) {
        }
        return msg;
    }


    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    protected abstract void successShow();

    protected abstract void errorShow();

    protected abstract void resolvableShow();
}
