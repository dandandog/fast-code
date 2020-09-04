package com.dandandog.framework.mapstruct.qualifier;

import com.dandandog.framework.common.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
public class QualifierDate {


    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatDate {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ParseDate {
    }


    @FormatDate
    public String format(LocalDateTime dateTime) {
        return Optional.ofNullable(dateTime).map(localDateTime ->
                DateTimeUtil.format(localDateTime, DateTimeUtil.NORM_DATETIME_FORMATTER)).
                orElse(null);
    }

    @ParseDate
    public LocalDateTime parse(String dateTime) {
        return Optional.ofNullable(dateTime).map(localDateTime ->
                DateTimeUtil.parse(localDateTime, DateTimeUtil.NORM_DATETIME_FORMATTER)).
                orElse(null);
    }

}
