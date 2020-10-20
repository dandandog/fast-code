package com.dandandog.framework.common.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JohnnyLiu
 */
public final class BrowserUtil {

    public static final String IE = "msie";
    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    private static final String USER_AGENT = "USER-AGENT";

    private BrowserUtil() {
        throw new AssertionError();
    }


    public static String getCurrent(HttpServletRequest request) {
        String userAgent = request.getHeader(USER_AGENT).toLowerCase();
        if (StrUtil.isNotBlank(userAgent)) {
            if (StrUtil.indexOfIgnoreCase(userAgent, CHROME) >= 0) {
                return CHROME;
            }
            if (StrUtil.indexOfIgnoreCase(userAgent, FIREFOX) >= 0) {
                return FIREFOX;
            }
            if (StrUtil.indexOfIgnoreCase(userAgent, IE) >= 0) {
                return IE;
            }
        }
        return null;
    }


    public static boolean isIe(HttpServletRequest request) {
        return StrUtil.equals(getCurrent(request), IE);
    }

    public static boolean isFirefox(HttpServletRequest request) {
        return StrUtil.equals(getCurrent(request), FIREFOX);
    }

    public static boolean isChrome(HttpServletRequest request) {
        return StrUtil.equals(getCurrent(request), CHROME);
    }
}
