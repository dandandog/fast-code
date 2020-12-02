package com.dandandog.framework.api.config;

import com.dandandog.framework.api.config.properties.ExpressTrackerProperties;
import com.dandandog.framework.api.expressTracker.api.KdniaoTrackQueryAPI;
import com.dandandog.framework.api.expressTracker.service.ExpressTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JohnnyLiu
 */

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({ExpressTrackerProperties.class})
public class ExpressTrackerConfig {


    private final ExpressTrackerProperties properties;

    @Bean
    public ExpressTrackerService expressTrackerService() {
        return new KdniaoTrackQueryAPI(properties.getExpressTrackerId(), properties.getAppKey());
    }


}
