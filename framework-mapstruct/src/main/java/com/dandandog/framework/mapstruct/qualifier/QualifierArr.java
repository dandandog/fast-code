package com.dandandog.framework.mapstruct.qualifier;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.mapstruct.config.properties.MapStructProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

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
    public String[] split(String in) {
        return StrUtil.isNotBlank(in) ? StrUtil.split(in, properties.getSeparator()) : null;
    }

    @Join
    public String join(String[] in) {
        if (ArrayUtil.isNotEmpty(in)) {
            return null;
        }
        Arrays.sort(in);
        return StrUtil.join(properties.getSeparator(), in);
    }

}
