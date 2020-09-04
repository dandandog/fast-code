package com.dandandog.framework.common.config.constant;

import org.springframework.http.HttpHeaders;

import java.util.Optional;

/**
 * @author JohnnyLiu
 */
public class FastCodeConstant {


    /**
     * swagger文档标题
     */
    public final static String SWAGGER_TITLE = "Fast Code API Documentation";

    /**
     * swagger项目地址
     */
    public final static String SWAGGER_URL = "https://github.com/dandandog";

    /**
     * swagger项目描述
     */
    public final static String SWAGGER_DESCRIPTION = "Documentation for Fast Code API";

    /**
     * swagger联系人
     */
    public final static String SWAGGER_CONTACT_USER = "Johnny";

    /**
     * swagger联系url
     */
    public final static String SWAGGER_CONTACT_URL = "https://github.com/dandandog/fastcode/issues";

    /**
     * swagger联系邮箱
     */
    public final static String SWAGGER_CONTACT_EMAIL = "704365036@qq.com";

    /**
     * 项目证书
     */
    public final static String PROJECT_LICENSE = "GNU General Public License v3.0";

    /**
     * 项目证书
     */
    public final static String PROJECT_LICENSE_URL = "https://github.com/dandandog/fastcode/master/LICENSE";


    /**
     * api 请求头名字
     */
    public final static String API_ACCESS_KEY_HEADER_NAME = "API-" + HttpHeaders.AUTHORIZATION;

    /**
     * api token 参数名
     */
    public final static String API_ACCESS_KEY_QUERY_NAME = "api_access_key";

    /**
     * admin 请求头名字
     */
    public final static String ADMIN_TOKEN_HEADER_NAME = "ADMIN-" + HttpHeaders.AUTHORIZATION;

    /**
     * 用户家目录
     */
    public final static String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 项目版本号
     */
    public static final String FAST_CODE_VERSION;

    /**
     * 未知版本号
     */
    public static final String UNKNOWN_VERSION = "unknown";

    static {
        FAST_CODE_VERSION = Optional.ofNullable(FastCodeConstant.class.getPackage().getImplementationVersion()).orElse(UNKNOWN_VERSION);
    }
}
