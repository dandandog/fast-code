package com.dandandog.framework.mapstruct.qualifier;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.mapstruct.config.properties.MapStructProperties;
import com.dandandog.framework.mapstruct.model.MapperUrl;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(MapStructProperties.class)
public class QualifierArrUrl {

    private final MapStructProperties properties;

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface AddDomain {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface DelDomain {
    }


    @AddDomain
    public List<MapperUrl> addDomain(String in) {
        List<String> urlStr = Splitter.on(properties.getSeparator()).trimResults().omitEmptyStrings().splitToList(in);
        return CollUtil.isNotEmpty(urlStr) ? urlStr.stream().map(s ->
                new MapperUrl(StrUtil.addPrefixIfNot(s, properties.getUrlDomain()))).collect(Collectors.toList()) : null;
    }

    @DelDomain
    public String delDomain(List<MapperUrl> in) {
        List<String> urls = CollUtil.isNotEmpty(in) ? in.stream().map(mapperUrl ->
                StrUtil.removePrefix(mapperUrl, properties.getUrlDomain())).collect(Collectors.toList()) : Lists.newArrayList();
        return CollUtil.isNotEmpty(urls) ? Joiner.on(properties.getSeparator()).skipNulls().join(urls) : null;
    }
}
