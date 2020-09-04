package com.dandandog.framework.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.web")
public class WebConfig {

    public WebConfig() {
        log.debug("Web config initialization");
    }
}
