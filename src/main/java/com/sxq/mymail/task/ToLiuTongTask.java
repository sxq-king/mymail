package com.sxq.mymail.task;

import com.sxq.mymail.aop.SystemControllerLog;
import com.sxq.mymail.common.Constants;
import com.sxq.mymail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ToLiuTongTask {

    @Resource
    private MailService mailService;


    @Scheduled(cron = "0 0 8 * * ?")
    @SystemControllerLog(description = "发给LSP")
    public void sendToLT(){
        String to = Constants.L_T;
        String subject = "傻逼,刘通";
        String html = "<html><body>" +
                "<h1>小黑子</h1>" +
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
