package com.dandandog.framework.api.config;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.dandandog.framework.api.config.properties.AlibabaMaProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JohnnyLiu
 */


@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AlibabaMaProperties.class)
@ConditionalOnProperty(prefix = "fast-code.alibaba-api", value = {"enabled"}, matchIfMissing = true)
public class AlibabaConfig implements InitializingBean {

    private final AlibabaMaProperties properties;

    @Bean
    public ApiExecutor getApiExecutor() {
        return new ApiExecutor(properties.getAppKey(), properties.getSecKey());
    }

    @Override
    public void afterPropertiesSet() {
        getApiExecutor();
    }
}
