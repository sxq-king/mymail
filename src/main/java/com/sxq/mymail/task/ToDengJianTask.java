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
public class ToDengJianTask {

    @Resource
    private MailService mailService;


    @Scheduled(cron = "0 0 8 * * ?")
    @SystemControllerLog(description = "发给dj")
    public void sendToLT(){
        String to = Constants.D_J;
        String subject = "沙雕,DengJian";
        String html = "<html><body>" +
                "<h1>该上班了</h1>" +
                "<p><h2 style='color: red;'>gif</h2><img style='width: 280px; height: 280px;' src='cid:test'</p>"+
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
