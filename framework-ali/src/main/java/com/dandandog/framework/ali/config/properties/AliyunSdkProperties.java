package com.dandandog.framework.ali.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: StephenZhang
 * @date: 2021-10-25 17:25
 */
@Data
@ConfigurationProperties("fast-code.aliyun")
public class AliyunSdkProperties {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String regionId;

    private String stsAccessKeyId;

    private String stsSecret;

    private String roleArn;

    private String roleSessionName;

    private String objectName;

    private String objectSourceName;
}
