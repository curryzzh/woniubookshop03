package com.woniuxy.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    //声明创建一个队列
    @Bean
    public Queue simpleQueue(){
        return new Queue("simple_queue");
    }


}
