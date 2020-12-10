package com.dandandog.framework.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.collection.CollUtil;
import com.dandandog.framework.wx.config.properties.WxMaProperties;
import com.dandandog.framework.wx.config.properties.WxPayProperties;
import com.dandandog.framework.wx.config.properties.WxProperties;
import com.dandandog.framework.wx.utils.WxUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(WxProperties.class)
@ConditionalOnProperty(prefix = "fast-code.wx", value = {"enabled"}, matchIfMissing = true)
public class WxConfig {

    private final WxProperties properties;

    @Bean
    public WxUtil wxUtil() {
        WxUtil wxUtil = new WxUtil(properties.getDefApp(), properties.getEvn());
        WxUtil.setMaServices(initMaConfig());
        WxUtil.setPayServices(initPayConfig());
        return wxUtil;
    }

    private Map<String, WxMaService> initMaConfig() {
        List<WxMaProperties> miniApps = this.properties.getMiniApps();
        if (CollUtil.isEmpty(miniApps)) {
            log.warn("微信小程序 - 未找到相关配置文件");
            return null;
        }
        return miniApps.stream().collect(Collectors.toMap(WxMaProperties::getName, properties -> new WxMaServiceImpl() {{
            setWxMaConfig(new WxMaDefaultConfigImpl() {{
                setAppid(properties.getAppId());
                setSecret(properties.getSecret());
                setToken(properties.getToken());
                setAesKey(properties.getAesKey());
                setMsgDataFormat(properties.getMsgDataFormat());
            }});
        }}));
    }

    private Map<String, WxPayService> initPayConfig() {
        List<WxPayProperties> pays = this.properties.getPays();
        if (CollUtil.isEmpty(pays)) {
            log.warn("微信支付 - 未找到相关配置文件");
            return null;
        }
        return pays.stream().collect(Collectors.toMap(WxPayProperties::getName, properties -> new WxPayServiceImpl() {{
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


}
