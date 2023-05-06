package com.woniuxy.qiantai.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendEmailMQConfig {


    @Bean
    public Queue sendEmailQueue(){
        return new Queue("send_email_queue");
    }


    @Bean
    public DirectExchange sendEmailExchange(){
        return new DirectExchange("send_email_exchange");
    }

    @Bean
    public Binding sendEmailBinding(){
        return BindingBuilder.bind(sendEmailQueue())
                .to(sendEmailExchange())
                .with("sendEmail");
    }

}
