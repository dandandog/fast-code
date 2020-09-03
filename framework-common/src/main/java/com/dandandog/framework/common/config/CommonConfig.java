package com.dandandog.framework.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.dandandog.framework.common")
public class CommonConfig {

    public CommonConfig() {
        log.debug("Common config initialization");
    }

}
