package com.woniuxy;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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


    @RequestMapping("/testBlock")
    @ResponseBody
    public String testBlock(){

        String exchangeName = "normal_exchange";
        String routingKey = "testDelay";
        String msg = "发送时间点"+new Date();



        String msgDelay10 = "消息预期延迟10s被消费" + msg;
        //发送消息,同时自定义消息的ttl
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msgDelay10, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置当前消息的ttl
                message.getMessageProperties().setExpiration("10000");

                return message;
            }
        });



        String msgDelay5 = "消息预期延迟5s被消费" + msg;
        //发送消息,同时自定义消息的ttl
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msgDelay5, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置当前消息的ttl
                message.getMessageProperties().setExpiration("5000");

                return message;
            }
        });

        return "ok";
    }





}
