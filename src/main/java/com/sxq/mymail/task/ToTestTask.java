package com.sxq.mymail.task;

import com.sxq.mymail.aop.SystemControllerLog;
import com.sxq.mymail.common.Constants;
import com.sxq.mymail.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song
 * @version 1.0
 * @date 2023/6/16 10:33
 * @description: TODO
 */
@Component
public class ToTestTask {

    @Resource
    private MailService mailService;

    @SystemControllerLog(description = "发送给sxq")
    @Scheduled(cron = "0 0 8 * * ?")
//    @Scheduled(cron = "0/2 * * * * ?")
    public void sendToSXQ(){
        String to = "2313939051@qq.com";
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
                "src/main/resources/static/早.gif"
        };
        boolean flag = mailService.sendWithImageHtml(to,subject,html,cids,filePaths);
        if(flag == false){
            System.out.println("发送失败");
        }else {
            System.out.println("发送成功");
        }
    }


}
