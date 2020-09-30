package com.dandandog.framework.captcha.service.impl;

import com.dandandog.framework.captcha.config.properties.CaptchaProperties;
import com.dandandog.framework.captcha.model.EmailCaptcha;
import com.dandandog.framework.captcha.service.CaptchaService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service("com.dandandog.framework.captcha.model.EmailCaptcha")
public class EmailCaptchaServiceImpl implements CaptchaService<EmailCaptcha> {


    @Resource
    private JavaMailSender mailSender;

    @Override
    public EmailCaptcha generate(CaptchaProperties captchaProperties) {
        try {
            MimeMessage parentMimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(parentMimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom("704365036@qq.com");
            mimeMessageHelper.setTo("704365036@qq.com");
            mimeMessageHelper.setSubject("主题：简单邮件");
            mimeMessageHelper.setText("测试邮件内容");
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception ignored) {
        }
        return null;
    }
}
