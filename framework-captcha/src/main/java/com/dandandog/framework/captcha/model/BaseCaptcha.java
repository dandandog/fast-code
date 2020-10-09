package com.dandandog.framework.captcha.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseCaptcha implements Serializable {

    private String code;

    private LocalDateTime expireTime;

    public boolean verify(String code) {
        return StrUtil.isNotEmpty(code) && getCode().toLowerCase().equals(code.toLowerCase());
    }
}
