package com.dandandog.framework.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPayProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JohnnyLiu
 */
@Configuration
@EnableConfigurationProperties({WxMaProperties.class, WxPayProperties.class})
public class WechatAppConfig {

    private final WxMaProperties maProperties;

    private final WxPayProperties payProperties;

    @Autowired
    public WechatAppConfig(WxMaProperties maProperties, WxPayProperties payProperties) {
        this.maProperties = maProperties;
        this.payProperties = payProperties;
    }


    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaDefaultConfigImpl wxMaService = new WxMaDefaultConfigImpl();
        wxMaService.setAppid(maProperties.getAppid());
        wxMaService.setSecret(maProperties.getSecret());
        wxMaService.setToken(maProperties.getToken());
        wxMaService.setAesKey(maProperties.getAesKey());
        wxMaService.setMsgDataFormat(maProperties.getMsgDataFormat());
        return wxMaService;
    }

    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(payProperties.getAppId());
        payConfig.setMchId(payProperties.getMchId());
        payConfig.setMchKey(payProperties.getMchKey());
        payConfig.setSubAppId(payProperties.getSubAppId());
        payConfig.setSubMchId(payProperties.getSubMchId());
        payConfig.setKeyPath(payProperties.getKeyPath());
        return payConfig;
    }


    @Bean
    public WxMaService wxMpService() {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaConfig());
        return service;
    }

    @Bean
    public WxPayService payService() {
        WxPayService payService = new WxPayServiceImpl();
        payService.setConfig(wxPayConfig());
        return payService;
    }
}
