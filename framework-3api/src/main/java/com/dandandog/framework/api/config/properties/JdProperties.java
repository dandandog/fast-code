package com.dandandog.framework.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 11:27
 */
@Data
@ConfigurationProperties("fast-code.jd-api")
public class JdProperties {

    private String username;

    private String password;

    private String clientId;

    private String clientSecret;

}
