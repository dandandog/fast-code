package com.dandandog.framework.mapstruct.qualifier;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
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
public class QualifierUrl {


    protected final MapStructProperties properties;


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

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface AddDomains {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface DelDomains {
    }

    @AddDomain
    public MapperUrl addDomain(String urlStr) {
        if (StrUtil.isNotBlank(urlStr)) {
            if (ArrayUtil.isNotEmpty(properties.getWhiteListDomains())) {
                return new MapperUrl(StrUtil.prependIfMissing(urlStr, properties.getUrlDomain(), true, properties.getWhiteListDomains()));
            } else {
                return new MapperUrl(StrUtil.addPrefixIfNot(urlStr, properties.getUrlDomain()));
            }
        }
        return null;
    }

    @DelDomain
    public String delDomain(MapperUrl url) {
        return ObjectUtil.isNotNull(url) ? StrUtil.removePrefix(url, properties.getUrlDomain()) : null;
    }


    @AddDomains
    public List<MapperUrl> addDomains(String in) {
        if (StrUtil.isBlank(in)) {
            return Lists.newArrayList();
        }
        List<String> urlStr = Splitter.on(properties.getSeparator()).trimResults().omitEmptyStrings().splitToList(in);
        return CollUtil.isNotEmpty(urlStr) ? urlStr.stream().map(this::addDomain).collect(Collectors.toList()) : Lists.newArrayList();
    }

    @DelDomains
    public String delDomains(List<MapperUrl> in) {
        if (CollUtil.isEmpty(in)) {
            return "";
        }
        List<String> urls = CollUtil.isNotEmpty(in) ? in.stream().map(this::delDomain).collect(Collectors.toList()) : Lists.newArrayList();
        return CollUtil.isNotEmpty(urls) ? Joiner.on(properties.getSeparator()).skipNulls().join(urls) : null;
    }

}
