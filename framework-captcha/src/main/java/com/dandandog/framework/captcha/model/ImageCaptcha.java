package com.dandandog.framework.captcha.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
public class ImageCaptcha extends BaseCaptcha {


    private byte[] imageBytes;

    private BufferedImage image;

    private String imageBase64;

    private String imageBase64Data;


    @Override
    public boolean verify(String code) {
        return super.verify(code) || getExpireTime().isAfter(LocalDateTime.now());
    }
}
