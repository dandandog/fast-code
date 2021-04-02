package com.dandandog.framework.ali.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.dandandog.framework.ali.config.properties.AliProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/4/2 10:25
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliProperties.class)
@ConditionalOnProperty(prefix = "fast-code.ali", value = {"enabled"}, matchIfMissing = true)
public class AliConfig {
    private final AliProperties properties;

    {
        Factory.setOptions(getOptions());
    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = properties.getProtocol();
        config.gatewayHost = properties.getGatewayHost();
        config.signType = properties.getSignType();
        config.appId = properties.getProtocol();
        config.merchantPrivateKey = properties.getMerchantPrivateKey();
        config.merchantCertPath = properties.getMerchantCertPath();
        config.alipayCertPath = properties.getAlipayCertPath();
        config.alipayRootCertPath = properties.getAlipayRootCertPath();
        log.info(config.toString());
        Factory.setOptions(config);
        return config;
    }
}
