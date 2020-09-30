package com.dandandog.framework.faces.config.properties;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fast-code.page")
public class PageProperties {

    private String login = FastCodeConstant.LOGIN_PAGE;

    private String loginFailed = FastCodeConstant.LOGIN_FAILED_PAGE;

    private String index = FastCodeConstant.INDEX_PAGE;

    private String error = FastCodeConstant.ERROR_PAGE;

    private String access = FastCodeConstant.ACCESS_PAGE;

    private String notFound = FastCodeConstant.NOT_FOUND_PAGE;

    private String suffix = FastCodeConstant.PAGE_SUFFIX;
}
