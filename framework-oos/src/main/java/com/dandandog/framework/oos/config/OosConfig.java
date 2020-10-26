package com.dandandog.framework.oos.config;

import com.dandandog.framework.oos.config.properties.OosProperties;
import com.dandandog.framework.oos.service.FileService;
import com.dandandog.framework.oos.service.impl.MinioServiceImpl;
import com.dandandog.framework.oos.service.impl.QiniuServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JohnnyLiu
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({OosProperties.class})
@ConditionalOnProperty(prefix = "fast-code.wx", value = {"enabled"}, matchIfMissing = true)
public class OosConfig {

    private final OosProperties oosProperties;

    @Bean
    public FileService fileService() {
        FileService service;
        switch (oosProperties.getType()) {
            case Minio:
                service = new MinioServiceImpl(oosProperties);
                break;
            case Qiniu:
                service = new QiniuServiceImpl(oosProperties);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + oosProperties.getType());
        }

        return service;
    }


}
