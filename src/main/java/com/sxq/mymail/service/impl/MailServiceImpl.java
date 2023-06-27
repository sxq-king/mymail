package com.sxq.mymail.service.impl;

import com.sxq.mymail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Auther: s x q
 * @Date: 2022/8/16 22:04
 * @Version: v1.0
 * 邮件服务接口实现类
 */
@Service
public class MailServiceImpl implements MailService {

    private final static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     *
     * @param to      接受方
     * @param subject 邮件标题
     * @param content 内容
     * @return
     */
    @Override
    public boolean send(String to, String subject, String content) {
        logger.info("## 准备发简单邮件给{} ##",to);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //发送邮件来源
        simpleMailMessage.setFrom(mailProperties.getUsername());
        //邮件发送目标
        simpleMailMessage.setTo(to);
        //设置标题
        simpleMailMessage.setSubject(subject);
        //设置内容
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("## 发送成功 ##");
        } catch (Exception e) {
            logger.info("## 发送失败 ##");
            return false;
        }
        return true;
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param html
     * @return
     */
    @Override
    public boolean sendWithHtml(String to, String subject, String html) {
        logger.info("## 准备发html邮件给{} ##",to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送来源
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            //邮件发送目标
            mimeMessageHelper.setTo(to);
            //邮件发送标题
            mimeMessageHelper.setSubject(subject);
            //设置内容，并设置html内容为true
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);
            logger.info("## 发送成功 ##");
        } catch (MessagingException e) {
            logger.info("## 发送失败 ##");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 发送带图片的html邮件
     * @param to
     * @param subject
     * @param cids
     * @param filePaths
     * @return
     */
    @Override
    public boolean sendWithImageHtml(String to, String subject,String html, String[] cids, String[] filePaths) {
        logger.info("## 准备发HtmlWithImage邮件给{} ##",to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            // 邮件发送目标
            mimeMessageHelper.setTo(to);
            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(html, true);

            //设置html中的图片
            for (int i = 0; i < cids.length; i++) {
                FileSystemResource file = new FileSystemResource(filePaths[i]);
                // addInline() 方法 cid 需要 html 中的 cid (Content ID) 对应，才能设置图片成功，
                mimeMessageHelper.addInline(cids[i], file);
            }
            javaMailSender.send(mimeMessage);
            logger.info("## 发送成功 ##");


        } catch (MessagingException e) {
            logger.info("## 发送失败 ##");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendWithWithEnclosure(String to, String subject, String content, String[] filePaths) {
        logger.info("## 准备发HtmlWithEnclosure邮件给{} ##",to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);

            //添加附件
            for (int i = 0; i < filePaths.length; i++) {
                FileSystemResource file = new FileSystemResource(filePaths[i]);
                String attachFileName = "附件" + (i+1);
                mimeMessageHelper.addAttachment(attachFileName,file);
            }
            javaMailSender.send(mimeMessage);
            logger.info("## 发送成功 ##");


        } catch (MessagingException e) {
            logger.info("## 发送失败 ##");
            e.printStackTrace();
        }
        return true;
    }
}
