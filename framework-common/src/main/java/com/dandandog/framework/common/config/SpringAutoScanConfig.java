package com.dandandog.framework.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.common")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Common config initialization");
    }

}
