package com.woniuxy;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeDirectConfig {

    @Bean
    public DirectExchange directExchangeDemo2(){
        return new DirectExchange("directExchangeDemo2");
    }

    @Bean
    public Queue directQueueDemo2(){
        return new Queue("directQueueDemo2");
    }

    @Bean
    public Binding directBindingDemo2(){
        return BindingBuilder
                .bind(directQueueDemo2())
                .to(directExchangeDemo2())
                .with("demo2");
    }



}
