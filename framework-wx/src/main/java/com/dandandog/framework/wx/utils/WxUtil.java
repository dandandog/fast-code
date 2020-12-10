package com.dandandog.framework.wx.utils;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.dandandog.framework.common.exception.FastCodeException;
import com.dandandog.framework.wx.model.WxMsgTemplate;
import com.github.binarywang.wxpay.service.RedpackService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Lists;
import lombok.Data;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Data
public class WxUtil {

    private static Map<String, WxMaService> maServices;

    private static Map<String, WxPayService> payServices;

    private static String evn;

    private static String appName;

    private static WxMaService wxMaService;

    private static WxPayService wxPayService;

    public WxUtil(String appName, String evn) {
        WxUtil.evn = evn;
        WxUtil.appName = appName;
    }


    private static WxMaService getMaService(String appName) {
        return Optional.ofNullable(maServices).map(services -> Optional.ofNullable(maServices.get(appName))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appName)));
    }

    private static WxPayService getPayService(String appName) {
        return Optional.ofNullable(payServices).map(services -> Optional.ofNullable(payServices.get(appName))).
                orElseThrow(() -> new FastCodeException("未启用小程序配置"))
                .orElseThrow(() -> new FastCodeException(String.format("未找到对应appid=[%s]的配置，请核实！", appName)));
    }


    public static void setMaServices(Map<String, WxMaService> maServices) {
        WxUtil.maServices = maServices;
    }

    public static void setPayServices(Map<String, WxPayService> payServices) {
        WxUtil.payServices = payServices;
    }

    public static WxMaService getMaService() {
        return getMaService(appName);
    }

    public static WxPayService getPayService() {
        return getPayService(appName);
    }


    public static WxMaUserService getUserService() {
        return getMaService().getUserService();
    }

    public static WxMaJscode2SessionResult getSessionInfo(String code) throws WxErrorException {
        return getMaService().getUserService().getSessionInfo(code);
    }

    public static WxMaUserInfo getUserInfo(String code, String encryptedData, String iv) throws WxErrorException {
        WxMaJscode2SessionResult session = getSessionInfo(code);
        return getMaService().getUserService().getUserInfo(session.getSessionKey(), encryptedData, iv);
    }

    public static WxMaMsgService getMsgService() {
        return getMaService().getMsgService();
    }

    public static RedpackService getRedpackService() {
        return getPayService().getRedpackService();
    }

    public static void sendSubscribeMsg(WxMsgTemplate template) throws WxErrorException {
        ArrayList<WxMaSubscribeMessage.Data> messages = Lists.newArrayList();
        template.getData().forEach((key, value) -> {
            messages.add(new WxMaSubscribeMessage.Data(key, value));
        });
        getMsgService().sendSubscribeMsg(WxMaSubscribeMessage.builder()
                .templateId(template.getId())
                .data(messages)
                .toUser(template.getToUser())
                .miniprogramState(evn)
                .build());
    }


}
