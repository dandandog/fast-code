package com.dandandog.framework.faces.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.annotation.MessageNotice;
import com.dandandog.framework.faces.annotation.MessageRequired;
import com.dandandog.framework.faces.annotation.MessageSeverity;
import com.dandandog.framework.faces.config.properties.MessageProperties;
import com.dandandog.framework.faces.exception.MessageResolvableException;
import com.dandandog.framework.faces.utils.MessageUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.primefaces.extensions.component.switchcase.Switch;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author JohnnyLiu
 */
@AllArgsConstructor
@EnableConfigurationProperties(MessageProperties.class)
public abstract class AbstractMessageAspect {


    private final MessageProperties properties;

    @Around("@annotation(messageRequired)")
    public Object showMessage(ProceedingJoinPoint joinPoint, MessageRequired messageRequired) throws Throwable {
        String title = MessageUtil.getMessageSource(properties.getTitle());
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        String messageDetail = null;
        MessageNotice notice = MessageNotice.GLOBAL;
        try {
            Object result = joinPoint.proceed();
            messageDetail = MessageUtil.getMessageSource(messageRequired.type().getSuccessCode(), result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            severity = FacesMessage.SEVERITY_ERROR;
            if ((e instanceof MessageResolvableException)) {
                MessageResolvableException e1 = (MessageResolvableException) e;
                messageDetail = MessageUtil.getMessageSource(e1.getCategory(), e1.getErrorCode(), e1.getParameters());
                notice = MessageNotice.MESSAGE;
            }
        } finally {
            notice = MessageNotice.DEFAULT.equals(messageRequired.notice()) ? notice : messageRequired.notice();
            if (checkSeverity(severity, messageRequired.severity())) {
                notifyScope(title, messageDetail, severity, notice);
            }
        }
        return null;
    }

    private boolean checkSeverity(FacesMessage.Severity severity, MessageSeverity... severities) {
        List<FacesMessage.Severity> collect = Stream.of(severities).map(MessageSeverity::getSeverity).collect(Collectors.toList());
        return CollUtil.contains(collect, severity);
    }


    private void notifyScope(String title, String message, FacesMessage.Severity severity, MessageNotice... notices) {
        for (MessageNotice notice : notices) {
            switch (notice) {
                case GLOBAL:
                    MessageUtil.addFacesMessage(globalShow(), title, message, severity);
                    break;
                case MESSAGE:
                    MessageUtil.addFacesMessage(messageShow(), title, message, severity);
                    break;
                case DIALOG:
                    MessageUtil.addFacesMessage(dialogShow(), title, message, severity);
                    break;
            }
        }
    }

    protected abstract String globalShow();

    protected abstract String messageShow();

    protected abstract String dialogShow();
}
