package com.dandandog.framework.captcha.config.properties;

import lombok.Data;

/**
 * @author JohnnyLiu
 */
@Data
public class EmailCaptchaProperties {

    private String host;

    private int port;

    private String username;

    private String password;

}
