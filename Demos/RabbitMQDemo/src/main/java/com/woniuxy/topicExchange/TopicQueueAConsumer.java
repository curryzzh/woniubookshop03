package com.woniuxy.topicExchange;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicQueueAConsumer {


    @RabbitListener(queues = "topic_Queue_1")
    //                消息对象            具体的消息
    public void onMsg(Message message, String msg){

        System.out.println("TopicQueueAConsumer "+msg);

    }


}
