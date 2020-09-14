package com.dandandog.framework.faces.config;

import com.dandandog.framework.faces.config.properties.PageProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(PageProperties.class)
public class PageConfig implements WebMvcConfigurer {

    private final PageProperties properties;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", properties.getIndex());
    }


    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> {
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, properties.getNotFound()));
            registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, properties.getError()));
            registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, properties.getAccess()));
            registry.addErrorPages(new ErrorPage(NoClassDefFoundError.class, properties.getError()));
        };
    }


}
