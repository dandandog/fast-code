package com.dandandog.framework.mapstruct.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author JohnnyLiu
 */
@Data
@ConfigurationProperties(prefix = "fast-code.map-struct")
public class MapStructProperties {

    private String urlDomain;

    private String[] whiteListDomains;

    private String separator = ",";


}
