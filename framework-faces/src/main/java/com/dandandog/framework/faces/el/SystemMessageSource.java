package com.dandandog.framework.faces.el;

import org.springframework.context.MessageSource;

/**
 * @author create by Johnny
 * @description com.johnny.web.faces.el
 * @date 七月 06,2019
 */
public class SystemMessageSource {

    private MessageSource messageSource;
    private String systemName;

    public SystemMessageSource(MessageSource messageSource, String systemName) {
        this.messageSource = messageSource;
        this.systemName = systemName;
    }

    public MessageSource getMessageSource() {
        return this.messageSource;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

}
