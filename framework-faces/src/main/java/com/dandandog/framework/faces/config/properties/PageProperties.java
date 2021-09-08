package com.dandandog.framework.faces.config.properties;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "fast-code.page")
public class PageProperties {

    private String login = FastCodeConstant.LOGIN_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String loginFailed = FastCodeConstant.LOGIN_FAILED_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String index = FastCodeConstant.INDEX_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String error = FastCodeConstant.ERROR_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String access = FastCodeConstant.ACCESS_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String notFound = FastCodeConstant.NOT_FOUND_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String forget = FastCodeConstant.FORGET_PAGE + FastCodeConstant.PAGE_SUFFIX;

    private String[] anonymous = new String[] {login, loginFailed, index, error, access, notFound, forget};

    private String suffix = FastCodeConstant.PAGE_SUFFIX;

}
