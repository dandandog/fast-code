package com.dandandog.framework.mapstruct.qualifier;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.mapstruct.config.properties.MapStructProperties;
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

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(MapStructProperties.class)
public class QualifierArr {


    private final MapStructProperties properties;


    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Split {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Join {
    }

    @Split
    public List<String> split(String in) {
        if (StrUtil.isNotBlank(in)) {
            List<String> strings = Splitter.on(properties.getSeparator()).trimResults().omitEmptyStrings().splitToList(in);
            return Lists.newArrayList(strings);
        }
        return Lists.newArrayList();
    }

    @Join
    public String join(List<String> in) {
        return CollUtil.isNotEmpty(in) ? Joiner.on(properties.getSeparator()).skipNulls().join(in) : "";
    }

}
