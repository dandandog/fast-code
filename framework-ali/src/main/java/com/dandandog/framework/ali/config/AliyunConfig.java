package com.dandandog.framework.ali.config;

import cn.hutool.core.util.StrUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.dandandog.framework.ali.config.properties.AliyunSdkProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: StephenZhang
 * @date: 2021-10-25 17:24
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliyunSdkProperties.class)
@ConditionalOnProperty(prefix = "fast-code.aliyun", value = {"enabled"}, matchIfMissing = true)
public class AliyunConfig implements InitializingBean {

    private final AliyunSdkProperties properties;

    private static final String SMS_ENDPOINT = "dysmsapi.aliyuncs.com";

    @Override
    public void afterPropertiesSet() {
        if (StrUtil.isNotEmpty(properties.getEndpoint()) && StrUtil.isNotEmpty(properties.getAccessKeyId()) && StrUtil.isNotEmpty(properties.getAccessKeySecret())) {
            oss();
        }
        if (StrUtil.isNotEmpty(properties.getRegionId()) && StrUtil.isNotEmpty(properties.getStsAccessKeyId()) && StrUtil.isNotEmpty(properties.getStsSecret())) {
            iacsClient();
        }
        try {
            createClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

    @Bean
    public IAcsClient iacsClient() {
        //构建一个阿里云客户端，用于发起请求。
        //构建阿里云客户端时需要设置AccessKey ID和AccessKey Secret。
        DefaultProfile profile = DefaultProfile.getProfile(properties.getRegionId(), properties.getStsAccessKeyId(), properties.getStsSecret());
        return new DefaultAcsClient(profile);
    }

    @Bean
    public Client createClient() throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(properties.getAccessKeyId())
                // 您的AccessKey Secret
                .setAccessKeySecret(properties.getAccessKeySecret());
        // 访问的域名
        config.endpoint = SMS_ENDPOINT;
        return new Client(config);
    }


}
