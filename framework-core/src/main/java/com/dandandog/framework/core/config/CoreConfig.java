package com.dandandog.framework.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.dandandog.farmework.core")
public class CoreConfig {

    public CoreConfig() {
        log.debug("Core config initialization");
    }
}
