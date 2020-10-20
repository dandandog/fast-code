package com.dandandog.framework.wx.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dandandog.framework.common.model.ApiErrorCode;
import com.dandandog.framework.wx.service.TokenService;
import com.dandandog.framework.wx.utils.JwtHeaderUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JohnnyLiu
 */
@Slf4j
@AllArgsConstructor
public class JwtFilter extends AuthenticatingFilter {

    private final TokenService tokenService;

    /**
     * 将 token 转成 JwtToken对象
     *
     * @param request  ServletRequest
     * @param response ServletResponse
     * @return AuthenticationToken
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        try {
            String token = JwtHeaderUtil.getAuthHeader();
            return tokenService.generateJwtToken(token);
        } catch (AuthenticationException e) {
            request.setAttribute("shiroLoginFailure", ApiErrorCode.NOT_LOGIN);
            String url = WebUtils.toHttp(request).getRequestURI();
            log.debug("isAccessAllowed url:{}", url);
            throw new TokenExpiredException(ApiErrorCode.NOT_LOGIN.getMsg());
        }
    }

    /**
     * 访问失败处理
     *
     * @param request  ServletRequest
     * @param response ServletResponse
     * @return boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // 返回401
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应码为401或者直接输出消息
        String url = httpServletRequest.getRequestURI();
        log.debug("onAccessDenied url：{}", url);
        return false;
    }

    /**
     * 此方法调用登陆，验证逻辑,期间executeLogin 里面会调用 createToken方法
     *
     * @param request     ServletRequest
     * @param response    ServletResponse
     * @param mappedValue Object
     * @return boolean
     */
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("isAccessAllowed url:{}", url);
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = executeLogin(request, response);
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 登录成功处理
     *
     * @param token    AuthenticationToken
     * @param subject  Subject
     * @param request  ServletRequest
     * @param response ServletResponse
     * @return boolean
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("鉴权成功,token:{},url:{}", token, url);
        String newToken = tokenService.refreshToken((JwtToken) token);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        JwtHeaderUtil.setAuthHeader(httpServletResponse, newToken);
        return true;
    }


    /**
     * 登录失败处理
     *
     * @param token    AuthenticationToken
     * @param e        AuthenticationException
     * @param request  ServletRequest
     * @param response ServletResponse
     * @return boolean
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("登录失败，token:" + token + ",error:" + e.getMessage(), e);
        return false;
    }

}
