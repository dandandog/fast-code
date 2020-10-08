package com.dandandog.framework.captcha.filter;

import com.dandandog.framework.captcha.exception.VerifyCaptchaException;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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

    private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private RequestMatcher requiresAuthenticationRequestMatcher;

    public CaptchaAuthenticationFilter() {
        this.requiresAuthenticationRequestMatcher = new AntPathRequestMatcher("/login", "POST");
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
                    throw new VerifyCaptchaException("captcha disabled");
                }
                if (!captcha.verify(code)) {
                    throw new VerifyCaptchaException("captcha error");
                }
            }
            chain.doFilter(request, response);
        } catch (VerifyCaptchaException e) {
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

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
