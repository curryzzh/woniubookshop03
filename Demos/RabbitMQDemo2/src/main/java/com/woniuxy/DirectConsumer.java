package com.woniuxy;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    @RabbitListener(queues = "directQueueDemo2")
    public void onMsg(String msg, Message message){
        System.out.println("DirectConsumer 接收到消息 "+msg);


    }




}
