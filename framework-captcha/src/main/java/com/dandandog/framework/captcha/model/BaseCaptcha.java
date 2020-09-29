package com.dandandog.framework.captcha.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseCaptcha {

    private String code;

    private LocalDateTime expireTime;
}
