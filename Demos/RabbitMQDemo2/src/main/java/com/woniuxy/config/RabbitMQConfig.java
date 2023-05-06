package com.woniuxy.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig implements InitializingBean {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {

        //confirm机制
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("correlationData "+correlationData);
                System.out.println("ack 消息有没有正常到达MQServer "+ack);
                System.out.println("cause ack为false时代表mq没有正常拿到消息的原因 "+cause);
            }
        });

    }


}
