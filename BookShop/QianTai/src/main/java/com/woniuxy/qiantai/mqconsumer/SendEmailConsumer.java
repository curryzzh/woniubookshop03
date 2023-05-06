package com.woniuxy.qiantai.mqconsumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailConsumer {



    @Autowired
    JavaMailSender javaMailSender;

    @RabbitListener(queues = "send_email_queue")
    public void onMsg(Message message, Channel channel, Map<String,String> info) throws IOException {

        try{
            //执行具体的业务逻辑

            //注册完成之后发送欢迎邮件
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(info.get("from"));
            mailMessage.setTo(info.get("to"));
            mailMessage.setSubject(info.get("subject"));
            mailMessage.setText(info.get("text"));

            TimeUnit.SECONDS.sleep(10);

            javaMailSender.send(mailMessage);

        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            return;
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }


}
