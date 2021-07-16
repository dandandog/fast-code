package com.dandandog.framework.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/16 11:02
 */

@Data
@ConfigurationProperties("fast-code.alibaba-api")
public class AlibabaMaProperties {

    private String appKey;

    private String secKey;
}
