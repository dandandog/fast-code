package com.dandandog.framework.captcha.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.ICaptcha;
import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.ImageCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import com.dandandog.framework.common.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("com.dandandog.framework.captcha.model.ImageCaptcha")
public class ImageCaptchaServiceImpl implements CaptchaService<ImageCaptcha> {

    @Resource
    private ICaptcha iCaptcha;


    @Override
    public ImageCaptcha generate(CaptchaProperties captchaProperties) {
        AbstractCaptcha captcha = (AbstractCaptcha) iCaptcha;
        captcha.createCode();
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
