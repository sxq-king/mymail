package com.sxq.mymail.service;

/**
 * @Auther: s x q
 * @Date: 2022/8/16 21:58
 * @Version: v1.0
 * 邮件服务接口类
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param to 接受方
     * @param subject 邮件标题
     * @param content 内容
     * @return
     */
    boolean send(String to,String subject,String content);

    /**
     * 发送Html邮件
     * @param to
     * @param subject
     * @param html
     * @return
     */
    boolean sendWithHtml(String to,String subject,String html);

    /**
     * 发送带有图片的html邮件
     * @param to
     * @param subject
     * @param cids
     * @param filePaths
     * @return
     */
    boolean sendWithImageHtml(String to,String subject,String html,String[] cids,String[] filePaths);

    /**
     * 发送带有附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @return
     */
    boolean sendWithWithEnclosure(String to,String subject,String content,String[] filePaths);


}
