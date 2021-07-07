package com.dandandog.framework.faces.annotation;

import javax.faces.application.FacesMessage;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/7 16:01
 */
public enum MessageSeverity {

    INFO(FacesMessage.SEVERITY_INFO),

    WARN(FacesMessage.SEVERITY_WARN),

    ERROR(FacesMessage.SEVERITY_ERROR),

    FATAL(FacesMessage.SEVERITY_FATAL);

    private final FacesMessage.Severity severity;

    MessageSeverity(FacesMessage.Severity severity) {
        this.severity = severity;
    }

    public FacesMessage.Severity getSeverity() {
        return severity;
    }
}
