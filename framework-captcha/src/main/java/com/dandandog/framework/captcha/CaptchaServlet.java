package com.dandandog.framework.captcha;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.ImageCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@AllArgsConstructor
public class CaptchaServlet extends HttpServlet {

    public static final String FILE_TYPE = "jpeg";

    private final CaptchaProperties properties;

    private final CaptchaService<?> baseCaptcha;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageCaptcha captcha = (ImageCaptcha) baseCaptcha.generate(properties);
        OutputStream outputStream = response.getOutputStream();
        request.getSession().setAttribute(properties.getKey(), captcha);
        ImageIO.write(captcha.getImage(), FILE_TYPE, outputStream);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
