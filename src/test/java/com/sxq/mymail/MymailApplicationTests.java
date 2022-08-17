package com.sxq.mymail;

import com.sxq.mymail.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MymailApplicationTests {
    @Autowired
    private MailService mailService;

    /**
     * 测试简单邮件发送
     */
    @Test
    void send() {
        //目标对象
        //刘通
        //String to = "j2568095536@Gmail.com";
        //梁银行
        String to = "1879705300@qq.com";
        //邮件标题
        String subject = "liangyinhang";
        //邮件内容
        String content = subject+",你个小黑子";
        boolean flag = mailService.send(to, subject, content);
        if(flag == false){
            System.out.println("发送失败");
        }else {
            System.out.println("发送成功");
        }
    }
    /**
     * 测试html邮件发送
     */
    @Test
    void sentHtml(){
        String to = "j2568095536@Gmail.com";
        String subject = "html,liutong";
        String html = "<html><body><h1>你个小黑子</h1></body></html>";
        boolean flag = mailService.sendWithHtml(to, subject, html);
        if(flag == false){
            System.out.println("发送失败");
        }else {
            System.out.println("发送成功");
        }
    }

    /**
     * 测试html带图片邮件发送
     */
    @Test
    void sendHtmlWithImage(){
        String to = "j2568095536@Gmail.com";
        String subject = "html,liutong";
        String html = "<html><body>" +
                "<h1>你个小黑子</h1>" +
                "<p><h2 style='color: red;'>图片</h2><img style='width: 280px; height: 280px;' src='cid:test'</p>"+
                "</body></html>";
        //cid要于src中cid一致
        String[] cids=new String[]{
                "test"
        };
        //文件路径
        String[] filePaths = new String[]{
                "C:\\Users\\sxq\\Pictures\\Camera Roll\\heizi.jpg"
        };
        boolean flag = mailService.sendWithImageHtml(to,subject,html,cids,filePaths);
        if(flag == false){
            System.out.println("发送失败");
        }else {
            System.out.println("发送成功");
        }
    }

    @Test
    void sendWithWithEnclosure(){
        String to = "j2568095536@Gmail.com";
        String subject = "liutong";
        String html = "<html><body><h1>你个小黑子</h1></body></html>";
        String[] filePaths = new String[]{
                "C:\\Users\\sxq\\Pictures\\Camera Roll\\heizi.jpg"
        };
        boolean flag = mailService.sendWithWithEnclosure(to,subject,html,filePaths);
        if(flag == false){
            System.out.println("发送失败");
        }else {
            System.out.println("发送成功");
        }

    }
}
