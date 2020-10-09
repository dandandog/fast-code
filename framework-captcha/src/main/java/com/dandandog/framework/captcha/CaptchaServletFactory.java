package com.dandandog.framework.captcha;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaServletFactory {


    private final CaptchaProperties properties;

    private final Map<String, CaptchaService<?>> serviceMap;


    public <T extends BaseCaptcha> CaptchaServlet generate(Class<T> tClass) {
        CaptchaService<?> captcha = serviceMap.get(tClass.getName());
        return new CaptchaServlet(properties, captcha);
    }

}
