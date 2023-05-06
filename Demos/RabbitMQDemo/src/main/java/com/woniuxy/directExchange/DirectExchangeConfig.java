package com.woniuxy.directExchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {


    @Bean
    public Queue directQueueA(){
        return new Queue("direct_Queue_A");
    }


    @Bean
    public Queue directQueueB(){
        return new Queue("direct_Queue_B");
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_exchange");
    }


    @Bean
    public Binding bindingAerror(){
        return BindingBuilder.bind(directQueueA())
                .to(directExchange())
                .with("error");
    }

    @Bean
    public Binding bindingBinfo(){
        return BindingBuilder.bind(directQueueB())
                .to(directExchange())
                .with("info");
    }


    @Bean
    public Binding bindingBerror(){
        return BindingBuilder.bind(directQueueB())
                .to(directExchange())
                .with("error");
    }


    @Bean
    public Binding bindingBwarning(){
        return BindingBuilder.bind(directQueueB())
                .to(directExchange())
                .with("warning");
    }


}
