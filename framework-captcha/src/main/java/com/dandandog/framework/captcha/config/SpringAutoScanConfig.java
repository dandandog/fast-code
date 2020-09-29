package com.dandandog.framework.captcha.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.dandandog.framework.captcha")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Captcha config initialization");
    }
}
