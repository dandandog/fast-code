package com.dandandog.framework.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.rest")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Web config initialization");
    }
}
