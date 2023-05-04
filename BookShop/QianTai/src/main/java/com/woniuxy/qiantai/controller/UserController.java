package com.woniuxy.qiantai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping("/requestEmailCode")
    public String requestEmailCode(String email){
        //校验邮箱地址是否合法
        String str="^" +
                "([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@" +
                "([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+" +
                "[\\.][A-Za-z]{2,3}" +
                "([\\.][A-Za-z]{2})?" +
                "$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            return "邮箱地址非法,请正确填写";
        }

        //发送向邮箱发送验证码
        String Code = "0000";
        sendEmail(email,"欢迎注册蜗牛书店","您的验证码是 "+Code+" ,有效期5分钟,请尽快使用");

        return "ok";
    }

    private void sendEmail(String email, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("woniumrwang@qq.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }


}

