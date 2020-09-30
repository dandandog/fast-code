package com.dandandog.framework.captcha.config.properties;

import lombok.Data;

@Data
public class ImageCaptchaProperties {

    private Integer width = 100;

    private Integer height = 30;

    private Integer codeCount = 4;

    private Integer thickness = 4;

}
