package com.dandandog.framework.mapstruct.qualifier;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.mapstruct.config.properties.MapStructProperties;
import com.dandandog.framework.mapstruct.model.MapperUrl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(MapStructProperties.class)
public class QualifierUrl {


    private final MapStructProperties properties;

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface AddPrefix {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface DelPrefix {
    }

    @AddPrefix
    public MapperUrl addPrefix(String urlStr) {
        return StrUtil.isNotBlank(urlStr) ? new MapperUrl(StrUtil.addPrefixIfNot(urlStr, properties.getUrlDomain())) : null;
    }

    @DelPrefix
    public String delPrefix(MapperUrl url) {
        return ObjectUtil.isNotNull(url) ? StrUtil.removePrefix(url, properties.getUrlDomain()) : null;
    }

}
