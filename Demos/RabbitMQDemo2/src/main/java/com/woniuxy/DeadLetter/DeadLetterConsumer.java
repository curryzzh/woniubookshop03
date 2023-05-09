package com.woniuxy.DeadLetter;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeadLetterConsumer {

    @RabbitListener(queues = "deadletter_queue")
    public void onMsg(String msg, Message message, Channel channel) throws IOException {
        System.out.println("DeadLetterConsumer 接收到消息 "+msg);

        System.out.println("执行逻辑: 如果订单未支付,那就取消订单;  否则啥也不干,当我没来过");

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }




}
