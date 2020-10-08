package com.dandandog.framework.captcha.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class SmsCaptcha extends BaseCaptcha {


    @Override
    public boolean verify(String code) {
        return super.verify(code);
    }
}
