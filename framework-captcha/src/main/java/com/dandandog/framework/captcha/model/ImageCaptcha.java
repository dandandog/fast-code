package com.dandandog.framework.captcha.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;


@Data
@EqualsAndHashCode(callSuper = true)
public class ImageCaptcha extends BaseCaptcha {


    private byte[] imageBytes;

    private BufferedImage image;

    private String imageBase64;

    private String imageBase64Data;


}
