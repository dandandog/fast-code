package com.dandandog.framework.mapstruct.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JohnnyLiu
 */
@Data
@ConfigurationProperties(prefix = "fast-code.map-struct")
public class MapStructProperties {

    private String urlDomain;

    private String separator = ",";


}
