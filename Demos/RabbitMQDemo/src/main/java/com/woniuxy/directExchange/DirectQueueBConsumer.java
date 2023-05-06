package com.woniuxy.directExchange;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectQueueBConsumer {


    @RabbitListener(queues = "direct_Queue_B")
    //                消息对象            具体的消息
    public void onMsg(Message message, String msg){

        System.out.println("DirectQueueBConsumer "+msg);

    }


}
