package com.dandandog.framework.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPayProperties;
import com.dandandog.framework.common.exception.FastCodeException;
import com.dandandog.framework.wx.config.properties.WxProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WxProperties.class)
public class WxConfig {

    private final WxProperties properties;


    private static Map<String, WxMaService> maServices = Maps.newConcurrentMap();

    private static Map<String, WxPayService> payServices = Maps.newConcurrentMap();

    @Autowired
    public WxConfig(WxProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        initMaConfig();
        initPayConfig();
    }

    private void initMaConfig() {
        List<WxMaProperties> maConfigs = this.properties.getMas();
        if (maConfigs == null) {
            log.warn("微信小程序 - 未找到相关配置文件");
            return;
        }
        maServices = maConfigs.stream().map(a -> {
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(a.getAppid());
            config.setSecret(a.getSecret());
            config.setToken(a.getToken());
            config.setAesKey(a.getAesKey());
            config.setMsgDataFormat(a.getMsgDataFormat());
            WxMaService wxMaService = new WxMaServiceImpl();
            wxMaService.setWxMaConfig(config);
            return wxMaService;
        }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a));
    }

    private void initPayConfig() {
        List<WxPayProperties> payConfigs = this.properties.getPays();
        if (payConfigs == null) {
            log.warn("微信小程序 - 未找到相关配置文件");
            return;
        }
        payServices = payConfigs.stream().map(a -> {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(payConfig.getAppId());
            payConfig.setMchId(payConfig.getMchId());
            payConfig.setMchKey(payConfig.getMchKey());
            payConfig.setSubAppId(payConfig.getSubAppId());
            payConfig.setSubMchId(payConfig.getSubMchId());
            payConfig.setKeyPath(payConfig.getKeyPath());
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            return wxPayService;
        }).collect(Collectors.toMap(s -> s.getConfig().getAppId(), a -> a));
    }


    public static WxMaService getMaService(String appId) {
        return Optional.ofNullable(maServices).map(services -> Optional.of(maServices.get(appId))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appId)));
    }

    public static WxPayService getPayService(String appId) {
        return Optional.ofNullable(payServices).map(services -> Optional.of(payServices.get(appId))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appId)));
    }
}
