package com.dandandog.framework.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.task")
public class SpringAutoScanConfig {

    public SpringAutoScanConfig() {
        log.debug("Task config initialization");
    }
}
