package com.dandandog.framework.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.dandandog.framework.mq")
public class SpringAutoScanConfig {


    public SpringAutoScanConfig() {
        log.debug("Framework mq ");
    }
}
