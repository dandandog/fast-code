package com.dandandog.framework.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.core")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Core config initialization");
    }
}
