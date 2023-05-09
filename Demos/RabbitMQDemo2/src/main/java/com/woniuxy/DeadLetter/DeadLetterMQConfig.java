package com.woniuxy.DeadLetter;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterMQConfig {

    //normal部分
    @Bean
    public DirectExchange normalExchange(){
        return new DirectExchange("normal_exchange");
    }

    @Bean
    public Queue normalQueue(){
        return QueueBuilder.durable("noraml_queue")
                .ttl(15*1000)
                .deadLetterExchange("deadletter_exchange")
                .deadLetterRoutingKey("delay")
                .build();
    }

    @Bean
    public Binding normalBinding(){
        return BindingBuilder.bind(normalQueue())
                .to(normalExchange())
                .with("testDelay");
    }


    //deadLetter部分
    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("deadletter_exchange");
    }

    @Bean
    public Queue deadLetterQueue(){
        return new Queue("deadletter_queue");
    }

    @Bean
    public Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("delay");
    }


}
