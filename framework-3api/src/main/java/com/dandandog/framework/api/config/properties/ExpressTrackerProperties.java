package com.dandandog.framework.api.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("fast-code.express-tracker")
public class ExpressTrackerProperties {

    private String expressTrackerId;

    private String appKey;

}
