package com.dandandog.framework.mapstruct.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.mapstruct")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Framework mapstruct ");
    }
}
