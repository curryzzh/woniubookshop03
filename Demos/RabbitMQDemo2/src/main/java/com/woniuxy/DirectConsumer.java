package com.woniuxy;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DirectConsumer {

    @RabbitListener(queues = "directQueueDemo2")
    public void onMsg(String msg, Message message, Channel channel) throws IOException {
        System.out.println("DirectConsumer 接收到消息 "+msg);


//        channel.basicAck();  //表示消费正常处理好了消息
//        channel.basicNack();  //表示消费者消息处理失败,可以通过参数指定消息是否还放回队列中去
//        channel.basicReject(); //拒绝消息  等效于单条消息的basicNack

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);

        //channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
    }




}
