package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo("1427047880@qq.com");
            simpleMailMessage.setSubject("一封简单的邮件");
            simpleMailMessage.setText("使用spring boot发送简单的邮件");
            jms.send(simpleMailMessage);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendHtmlEmail")
    public String sendHtmlEmail(){
        MimeMessage message = null;
        try{
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo("1427047880@qq.com");
            helper.setSubject("html类型邮件测试");
            StringBuffer buffer = new StringBuffer("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>");
            helper.setText(buffer.toString(), true);
            jms.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sendAttachmentsMail")
    public String sendAttachmentsMail(){
        MimeMessage message = null;
        try{
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message , true);
            helper.setFrom(from);
            helper.setTo("1036317945@qq.com");
            helper.setSubject("带附件的邮件");
            helper.setText("查看详情见附件");
            FileSystemResource file = new FileSystemResource(new File("D:\\IdeaProjects\\learnSpringAll\\Spring-Boot-Email\\src\\main\\resources\\static\\file\\项目文档.docx"));
            helper.addAttachment("项目文档.docx",file);
            jms.send(message);
            return "发送成功！";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sendInlineMail")
    public String sendInlineMail(){
        MimeMessage message = null;
        try{
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo("1427047880@qq.com");
            helper.setSubject("一封携带静态资源的邮件");
            helper.setText("<html><body>图片：<img src='cid:img' /></body></html>", true);
            FileSystemResource resource = new FileSystemResource(new File("D:\\IdeaProjects\\learnSpringAll\\Spring-Boot-Email\\src\\main\\resources\\static\\images\\music4.png"));
            helper.addInline("img", resource);
            jms.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sendTemplateEmail")
    public String sendTemplateEmail(String code){
        MimeMessage message = null;
        try{
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo("1036317945@qq.com");
            helper.setSubject("恭喜，中奖了！");
            Context context = new Context();
            context.setVariable("code", code);
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template);
            jms.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
