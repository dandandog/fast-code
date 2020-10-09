package com.dandandog.framework.captcha.filter;

import com.dandandog.framework.captcha.exception.CaptchaVerifyException;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import com.dandandog.framework.common.exception.FrameworkException;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CaptchaAuthenticationFilter extends GenericFilterBean {


    @Setter
    private String captchaParameter = "captcha";

    private final CaptchaFailureHandler failureHandler;

    private RequestMatcher requiresAuthenticationRequestMatcher;

    public CaptchaAuthenticationFilter(CaptchaFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        this.requiresAuthenticationRequestMatcher = new AntPathRequestMatcher("/login", "POST");
    }

    public CaptchaAuthenticationFilter(AntPathRequestMatcher antPathRequestMatcher, CaptchaFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        this.requiresAuthenticationRequestMatcher = antPathRequestMatcher;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            if (this.requiresAuthentication(request, response)) {
                String code = obtainCode(request);
                BaseCaptcha captcha = obtainCaptcha(request);
                if (captcha == null) {
                    throw new CaptchaVerifyException("captcha disabled");
                }
                if (!captcha.verify(code)) {
                    throw new CaptchaVerifyException("captcha error");
                }
            }
            chain.doFilter(request, response);
        } catch (CaptchaVerifyException e) {
            this.unsuccessfulAuthentication(request, response, e);
        }
    }

    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(this.captchaParameter);
    }

    @Nullable
    protected BaseCaptcha obtainCaptcha(HttpServletRequest request) {
        return (BaseCaptcha) request.getSession().getAttribute(this.captchaParameter);
    }

    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return this.requiresAuthenticationRequestMatcher.matches(request);
    }

    public final void setRequiresAuthenticationRequestMatcher(RequestMatcher requestMatcher) {
        Assert.notNull(requestMatcher, "requestMatcher cannot be null");
        this.requiresAuthenticationRequestMatcher = requestMatcher;
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FrameworkException failed) {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }


    public interface CaptchaFailureHandler {
        void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, FrameworkException failed);
    }

}
