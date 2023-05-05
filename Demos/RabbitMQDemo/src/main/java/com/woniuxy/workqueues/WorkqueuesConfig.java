package com.woniuxy.workqueues;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkqueuesConfig {

    //声明创建一个队列
    @Bean
    public Queue worksQueue(){
        return new Queue("works_queue");
    }


}
