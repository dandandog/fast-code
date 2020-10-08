package com.dandandog.framework.captcha;

import com.dandandog.framework.captcha.model.ImageCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class CaptchaServlet extends HttpServlet {

    public static final String FILE_TYPE = "jpeg";

    @Autowired
    private CaptchaFactory captchaFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageCaptcha captcha = captchaFactory.generate(ImageCaptcha.class, request);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(captcha.getImage(), FILE_TYPE, outputStream);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
