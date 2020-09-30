package com.dandandog.framework.captcha.service.impl;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.SmsCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import org.springframework.stereotype.Service;

@Service("com.dandandog.framework.captcha.model.SmsCaptcha")
public class SmsCaptchaServiceImpl implements CaptchaService<SmsCaptcha> {

    @Override
    public SmsCaptcha generate(CaptchaProperties captchaProperties) {
        return null;
    }









}
