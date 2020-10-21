package com.dandandog.framework.captcha.filter;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.captcha.exception.CaptchaVerifyException;
import com.dandandog.framework.captcha.model.BaseCaptcha;
import com.dandandog.framework.common.exception.FrameworkException;
import lombok.Setter;
import org.springframework.lang.Nullable;
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

    public CaptchaAuthenticationFilter(CaptchaFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            if (StrUtil.equals("/login", request.getServletPath())
                    && StrUtil.equalsIgnoreCase(request.getMethod(), "post")) {
                String code = obtainCode(request);
                BaseCaptcha captcha = obtainCaptcha(request);
                if (captcha == null) {
                    throw new CaptchaVerifyException("captcha disabled");
                }
                boolean isVerify = captcha.verify(code);
                this.cleanCaptcha(request);
                if (!isVerify) {
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

    private void cleanCaptcha(HttpServletRequest request) {
        request.getSession().removeAttribute(this.captchaParameter);
    }


    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FrameworkException failed) {
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }


    public interface CaptchaFailureHandler {
        void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, FrameworkException failed);
    }

}
