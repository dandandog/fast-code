package com.dandandog.framework.wx.utils;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.wx.config.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
public class JwtHeaderUtil {

    private static String tokenHeader;

    @Autowired
    public JwtHeaderUtil(JwtProperties jwtProperties) {
        tokenHeader = jwtProperties.getTokenHeader();
        log.debug("tokenHead:{}", tokenHeader);
    }

    /**
     * 从请求头或者请求参数中
     *
     * @return String
     */
    public static String getAuthHeader() {
        return getAuthHeader(getRequest());
    }

    /**
     * 从请求头或者请求参数中
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getAuthHeader(HttpServletRequest request) {
        request = Optional.ofNullable(request).orElseThrow(() -> new IllegalArgumentException("request can not be null"));
        String token = request.getHeader(tokenHeader);
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter(tokenHeader);
            if (StrUtil.isEmpty(token)) {
                throw new AuthenticationException("Token cannot be empty");
            }
        }
        return token;
    }


    /**
     * 从请求头或者请求参数中
     *
     * @param response HttpServletRequest
     * @param token    String
     */
    public static void setAuthHeader(HttpServletResponse response, String token) {
        response.setHeader(tokenHeader, token);
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

}
