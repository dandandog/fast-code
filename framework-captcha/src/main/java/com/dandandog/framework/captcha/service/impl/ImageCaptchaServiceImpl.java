package com.dandandog.framework.captcha.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.util.RandomUtil;
import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.config.properties.ImageCaptchaProperties;
import com.dandandog.framework.captcha.model.ImageCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import com.dandandog.framework.common.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("com.dandandog.framework.captcha.model.ImageCaptcha")
public class ImageCaptchaServiceImpl implements CaptchaService<ImageCaptcha> {

    @Override
    public ImageCaptcha generate(CaptchaProperties captchaProperties) {
        ImageCaptchaProperties image = captchaProperties.getImage();
        AbstractCaptcha captcha = null;
        int i = RandomUtil.randomInt(3);

        switch (i) {
            case 0:
                captcha = CaptchaUtil.createShearCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
                break;
            case 1:
                captcha = CaptchaUtil.createCircleCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
                break;
            default:
                captcha = CaptchaUtil.createLineCaptcha(image.getWidth(), image.getHeight(), image.getCodeCount(), image.getThickness());
                break;
        }

        ImageCaptcha imageCaptcha = new ImageCaptcha();
        imageCaptcha.setCode(captcha.getCode());
        imageCaptcha.setImage(captcha.getImage());
        imageCaptcha.setImageBytes(captcha.getImageBytes());
        imageCaptcha.setImageBase64(captcha.getImageBase64());
        imageCaptcha.setImageBase64Data(captcha.getImageBase64Data());
        imageCaptcha.setExpireTime(DateTimeUtil.plusMinutes(LocalDateTime.now(), captchaProperties.getExpireIn()));
        return imageCaptcha;
    }

}
