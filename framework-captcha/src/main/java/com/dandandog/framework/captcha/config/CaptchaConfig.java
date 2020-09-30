package com.dandandog.framework.captcha.config;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.util.RandomUtil;
import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.config.properties.EmailCaptchaProperties;
import com.dandandog.framework.captcha.config.properties.ImageCaptchaProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaConfig {

    private final CaptchaProperties properties;

    @Bean
    public ICaptcha iCaptcha() {
        ImageCaptchaProperties image = properties.getImage();
        int i = RandomUtil.randomInt(3);
        switch (i) {
            case 0:
                return CaptchaUtil.createShearCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
            case 1:
                return CaptchaUtil.createCircleCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
            default:
                return CaptchaUtil.createLineCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
        }
    }
}
