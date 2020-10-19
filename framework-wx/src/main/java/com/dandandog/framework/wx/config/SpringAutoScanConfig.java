package com.dandandog.framework.wx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.wx")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("wx config initialization");
    }
}
