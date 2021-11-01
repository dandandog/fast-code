package com.dandandog.framework.ali.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
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

    @Override
    public void afterPropertiesSet() {
        oss();
        iacsClient();
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


}
