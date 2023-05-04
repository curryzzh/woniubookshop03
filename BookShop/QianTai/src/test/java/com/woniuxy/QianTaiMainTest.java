package com.woniuxy;

//import com.woniuxy.qiantai.controller.BooktypeController;
import com.woniuxy.servicelayer.service.BooktypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
public class QianTaiMainTest {

    @Autowired
    BooktypeService booktypeService;
//
//    @Autowired
//    BooktypeController booktypeController;

    @Test
    void testGeneratorCode(){
        System.out.println( booktypeService.list() );
    }


    @Autowired
    JavaMailSender  javaMailSender;

    @Test
    void testEmail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("woniumrwang@qq.com");
        message.setTo("wangnimvp@163.com");
        message.setSubject("主题就是我");
        message.setText("我是邮件的具体内容"+new Date());

        javaMailSender.send(message);
    }


}
