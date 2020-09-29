package com.dandandog.framework.captcha.service.impl;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.EmailCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import org.springframework.stereotype.Service;

@Service("com.dandandog.framework.captcha.model.EmailCaptcha")
public class EmailCaptchaServiceImpl implements CaptchaService<EmailCaptcha> {


    @Override
    public EmailCaptcha generate(CaptchaProperties captchaProperties) {
        return null;
    }
}
