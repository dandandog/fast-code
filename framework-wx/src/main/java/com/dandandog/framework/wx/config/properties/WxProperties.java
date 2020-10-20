package com.dandandog.framework.wx.config.properties;

import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPayProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author JohnnyLiu
 */
@Data
@ConfigurationProperties(prefix = "fast-code.wx")
public class WxProperties {

    List<WxMaProperties> mas;

    List<WxPayProperties> pays;


}
