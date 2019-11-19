package com.project.template.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件工具类
 *
 * 注意：本案例之演示最基本的文本邮件，若需要发送Html、模板类的邮件自行参考网文
 *
 * @author suibin.wu
 */
@Slf4j
@Component
public class EmailUtil {


    @Resource
    private JavaMailSender mailSender;


    @Value("${mail.fromMail.addr}")
    private String from;


    /**
     * Send simple mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("邮件：{} 发送成功", subject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
