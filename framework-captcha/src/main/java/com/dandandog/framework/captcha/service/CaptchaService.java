package com.dandandog.framework.captcha.service;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.BaseCaptcha;

public interface CaptchaService<T extends BaseCaptcha> {

    BaseCaptcha generate(CaptchaProperties captchaProperties);

}
