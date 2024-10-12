package com.dshvv.blogserver.utils;

import freemarker.template.Template;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  邮件发送
 *  参考： https://www.cnblogs.com/zhou-test/p/10130499.html
 *  参考： https://www.cnblogs.com/sun2020/p/13961554.html
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-08
 */
@Component
public class EmailHelper{
    @Autowired
    JavaMailSender javaMailSender;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * 彩色邮件发送
     */
    public void sendHtmlEmail(String email)  throws Exception  {
        // 构建一个邮件对象 MimeMessageHelper ps SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("【花呗】注册验证码"); // 设置邮件主题
        mimeMessageHelper.setFrom("960423114@qq.com"); // 设置邮件发送者，这个跟application.yml中设置的要一致
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSentDate(new Date());  // 设置邮件发送日期
        Template template = configurer.getConfiguration().getTemplate("verify-email.html"); // 设置邮件的正文
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, null);
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);// 发送邮件
    }

    /**
     * 彩色邮件发送
     */
    public void sendHtmlEmail(String email, String data, String templateName)  throws Exception  {
        // 模板注入数据实体
        Map<String, Object> map = new HashMap<>();
        map.put("content",data);

        // 构建一个邮件对象 MimeMessageHelper ps SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("来自【花贝】的评论回复"); // 设置邮件主题
        mimeMessageHelper.setFrom("869043928@qq.com"); // 设置邮件发送者，这个跟application.yml中设置的要一致
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSentDate(new Date());  // 设置邮件发送日期
        Template template = configurer.getConfiguration().getTemplate(templateName+".html"); // 设置邮件的正文
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);// 发送邮件
    }

    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(String email, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("收到一条【花贝】的消息");
        message.setFrom("869043928@qq.com");
        message.setTo(email);
        message.setSentDate(new Date());
        message.setText(content);
        javaMailSender.send(message);
    }
}
