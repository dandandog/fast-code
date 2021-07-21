package com.dandandog.framework.api.config;

import com.dandandog.framework.api.config.properties.JdProperties;
import com.dandandog.framework.api.jdopenapi.api.JdApiExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 11:26
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(JdProperties.class)
@ConditionalOnProperty(prefix = "fast-code.jd-api", value = {"enabled"}, matchIfMissing = true)
public class JdConfig {

    private final JdProperties properties;


    @Bean
    public JdApiExecutor jdOpenApiService() {
        return new JdApiExecutor(properties);
    }


}
