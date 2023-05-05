package com.woniuxy.simple;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class SimpleConsumer {

    @RabbitListener(queues = "simple_queue")
    //                消息对象            具体的消息
    public void onMsg(Message message, String msg){

        System.out.println(message);
        System.out.println(msg);

    }


}
