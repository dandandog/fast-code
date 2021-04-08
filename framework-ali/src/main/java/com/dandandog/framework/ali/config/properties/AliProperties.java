package com.dandandog.framework.ali.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/4/2 10:32
 */
@Data
@ConfigurationProperties(prefix = "fast-code.ali")
public class AliProperties {

    /**
     * 协议：https
     */
    private String protocol = "https";
    /**
     * 网关主机：openapi.alipay.com
     */
    private String gatewayHost = "openapi.alipay.com";

    /**
     * 签名类型：RSA2
     */
    private String signType = "RSA2";

    /**
     * 支付宝小程序id
     */
    private String appId;

    /**
     * 私钥
     */
    private String merchantPrivateKey;

    /**
     * 应用公钥证书文件路径
     */
    private String merchantCertPath;

    /**
     * 支付宝公钥证书文件路径
     */
    private String alipayCertPath;

    /**
     * 支付宝根证书文件路径
     */
    private String alipayRootCertPath;

    /**
     * 可设置异步通知接收服务地址（可选）
     */
    private String notifyUrl;

    /**
     * 接口内容加密方式，例如：aa4BtZ4tspm2wnXLb1ThQA== （可选）
     */
    private String encryptKey;


}
