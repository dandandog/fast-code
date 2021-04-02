package com.dandandog.framework.ali.config.properties;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/4/2 10:32
 */
@Data
public class AliProperties {

    /**
     * 小程序名字
     */
    private String name;
    /**
     * 设置微信小程序的appId
     */
    private String appId;

    /**
     * 设置微信小程序的Secret
     */
    private String secret;

    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;

    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

}
