package com.woniuxy.fanoutExchange;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutQueueAConsumer {


    @RabbitListener(queues = "fanout_Queue_A")
    //                消息对象            具体的消息
    public void onMsg(Message message, String msg){

        System.out.println("FanoutQueueAConsumer "+msg);

    }


}
