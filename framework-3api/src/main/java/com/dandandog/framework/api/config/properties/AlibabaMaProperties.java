package com.dandandog.framework.api.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("fast-code.alibaba-api")
public class AlibabaMaProperties {
    private String appKey;

    private String secKey;

}
