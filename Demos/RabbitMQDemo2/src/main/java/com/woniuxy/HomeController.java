package com.woniuxy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String root(){
        return "index";
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public String send(String exchangeName,String routingKey,String msg){

        rabbitTemplate.convertAndSend(exchangeName,routingKey,msg);

        return "ok";
    }


}
