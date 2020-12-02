package com.dandandog.framework.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Johnny
 */
@Slf4j
@ComponentScan("com.dandandog.framework.api")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("API config initialization");
    }
}
