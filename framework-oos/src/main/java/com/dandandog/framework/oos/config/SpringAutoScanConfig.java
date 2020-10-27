package com.dandandog.framework.oos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.oos")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Framework oos");
    }
}
