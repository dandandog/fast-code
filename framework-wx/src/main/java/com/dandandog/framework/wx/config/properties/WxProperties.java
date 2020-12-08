package com.dandandog.framework.wx.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author JohnnyLiu
 */
@Data
@ConfigurationProperties(prefix = "fast-code.wx")
public class WxProperties {

    boolean enabled;

    List<WxMaProperties> miniApps;

    List<WxPayProperties> pays;

}
