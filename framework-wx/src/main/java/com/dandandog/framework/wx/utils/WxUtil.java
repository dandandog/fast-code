package com.dandandog.framework.wx.utils;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Data
public class WxUtil {

    private static Map<String, WxMaService> maServices = Maps.newConcurrentMap();

    private static Map<String, WxPayService> payServices = Maps.newConcurrentMap();

    public WxUtil(){

    }


}
