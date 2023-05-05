package com.woniuxy.fanoutExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {
//
//    队列A   及对应消费者
//    队列B   及对应消费者
//    交换机  (广播模式交换机)
//    交换和队列的绑定关系 2个

    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanout_Queue_A");
    }


    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanout_Queue_B");
    }


    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_exchange");
    }


    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }


}
