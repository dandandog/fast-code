package com.dandandog.framework.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnnyLiu
 */
@Slf4j
@ComponentScan("com.dandandog.framework.cache")
public class CacheConfig {

    public CacheConfig() {
        log.debug("Cache config initialization");
    }

}
