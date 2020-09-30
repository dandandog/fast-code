package com.dandandog.framework.captcha;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@AllArgsConstructor
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaFactory {


    private final CaptchaProperties properties;

    private final Map<String, CaptchaService<?>> serviceMap;


    public <T extends BaseCaptcha> T generate(Class<T> tClass, HttpServletRequest request) {
        BaseCaptcha captcha = serviceMap.get(tClass.getName()).generate(properties);
        request.getSession().setAttribute(properties.getKey(), captcha);
        return (T) captcha;
    }

}
