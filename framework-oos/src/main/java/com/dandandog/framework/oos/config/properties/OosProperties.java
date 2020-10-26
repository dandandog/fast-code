package com.dandandog.framework.oos.config.properties;


import com.dandandog.framework.oos.model.OosType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JohnnyLiu
 */
@Data
@ConfigurationProperties(prefix = "fast-code.oos")
public class OosProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String defBucket;

    private OosType type = OosType.Qiniu;

}
