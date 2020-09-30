package com.dandandog.framework.captcha.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("fast-code.captcha")
public class CaptchaProperties {

    private String key = "captcha";

    private String type = "IMAGE";

    private Long expireIn = 5L;

    private ImageCaptchaProperties image = new ImageCaptchaProperties();

    private SmsCaptchaProperties sms = new SmsCaptchaProperties();


}
