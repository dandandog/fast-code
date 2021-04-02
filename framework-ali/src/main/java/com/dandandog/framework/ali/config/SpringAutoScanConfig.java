package com.dandandog.framework.ali.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/4/2 10:27
 */
@Slf4j
@ComponentScan("com.dandandog.framework.ali")
public class SpringAutoScanConfig {
    public SpringAutoScanConfig() {
        log.debug("Ali config initialization");
    }
}
