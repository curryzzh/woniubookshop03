package com.woniuxy;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailConsumer {


    @RabbitListener(queues = "send_email_queue")
    public void onMsg(Message message, Channel channel, Map<String,String> info) throws IOException {

        try{
            //执行具体的业务逻辑

            System.out.println(info);

        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            return;
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }


}
