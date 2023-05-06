package com.woniuxy.topicExchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {


    @Bean
    public Queue topicQueue1(){
        return new Queue("topic_Queue_1");
    }


    @Bean
    public Queue topicQueue2(){
        return new Queue("topic_Queue_2");
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_exchange");
    }


    @Bean
    public Binding binding1orange(){
        return BindingBuilder.bind(topicQueue1())
                .to(topicExchange())
                .with("*.orange.*");
    }

    @Bean
    public Binding binding2rabbit(){
        return BindingBuilder.bind(topicQueue2())
                .to(topicExchange())
                .with("*.*.rabbit");
    }


    @Bean
    public Binding binding2lazy(){
        return BindingBuilder.bind(topicQueue2())
                .to(topicExchange())
                .with("lazy.#");
    }



}
