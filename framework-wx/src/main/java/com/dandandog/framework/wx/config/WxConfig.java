package com.dandandog.framework.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.collection.CollUtil;
import com.dandandog.framework.common.exception.FastCodeException;
import com.dandandog.framework.wx.config.properties.WxMaProperties;
import com.dandandog.framework.wx.config.properties.WxPayProperties;
import com.dandandog.framework.wx.config.properties.WxProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "fast-code.wx", value = {"enabled"}, matchIfMissing = true)
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
        List<WxMaProperties> miniApps = this.properties.getMiniApps();
        if (CollUtil.isEmpty(miniApps)) {
            log.warn("微信小程序 - 未找到相关配置文件");
            return;
        }
        maServices = miniApps.stream().collect(Collectors.toMap(WxMaProperties::getName, properties -> new WxMaServiceImpl() {{
            setWxMaConfig(new WxMaDefaultConfigImpl() {{
                setAppid(properties.getAppId());
                setSecret(properties.getSecret());
                setToken(properties.getToken());
                setAesKey(properties.getAesKey());
                setMsgDataFormat(properties.getMsgDataFormat());
            }});
        }}));
    }

    private void initPayConfig() {
        List<WxPayProperties> pays = this.properties.getPays();
        if (CollUtil.isEmpty(pays)) {
            log.warn("微信支付 - 未找到相关配置文件");
            return;
        }
        payServices = pays.stream().collect(Collectors.toMap(WxPayProperties::getName, properties -> new WxPayServiceImpl() {{
            setConfig(new WxPayConfig() {{
                setAppId(properties.getAppId());
                setMchId(properties.getMchId());
                setMchKey(properties.getMchKey());
                setSubAppId(properties.getSubAppId());
                setSubMchId(properties.getSubMchId());
                setKeyPath(properties.getKeyPath());
                setNotifyUrl(properties.getNotify());
            }});
        }}));
    }

    public static WxMaService getMaService(String appName) {
        return Optional.ofNullable(maServices).map(services -> Optional.ofNullable(maServices.get(appName))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appName)));
    }

    public static WxPayService getPayService(String appName) {
        return Optional.ofNullable(payServices).map(services -> Optional.ofNullable(payServices.get(appName))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appName)));
    }
}
