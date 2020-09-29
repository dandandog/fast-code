package com.dandandog.framework.captcha.filter;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.captcha.exception.VerifyCaptchaException;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import lombok.Setter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class CaptchaFilter extends GenericFilterBean {


    @Setter
    private String sessionKey;

    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            boolean isAjaxRequest = false;
            if (!StrUtil.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                isAjaxRequest = true;
            }
            if (!isAjaxRequest) {
                validate(request);
                chain.doFilter(request, response);
            }
        } catch (VerifyCaptchaException e) {
            if (authenticationFailureHandler != null) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
    }

    private void validate(HttpServletRequest request) throws VerifyCaptchaException {
        String requestCaptcha = request.getParameter(sessionKey);
        BaseCaptcha genCaptcha = (BaseCaptcha) request.getSession().getAttribute(sessionKey);
        if ("POST".equalsIgnoreCase(request.getMethod()) && "/login".equals(request.getServletPath())) {
            if (StrUtil.isEmpty(requestCaptcha))
                throw new VerifyCaptchaException("captcha not empty");
            if (genCaptcha == null)
                throw new VerifyCaptchaException("captcha error");
            if (genCaptcha.getExpireTime().isBefore(LocalDateTime.now()))
                throw new VerifyCaptchaException("captcha time out");
            if (!genCaptcha.getCode().toLowerCase().equals(requestCaptcha.toLowerCase()))
                throw new VerifyCaptchaException("captcha input error");
        }
    }
}
