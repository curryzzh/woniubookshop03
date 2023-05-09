package com.woniuxy.DeadLetter;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadLetterPluginMQConfig {


    @Bean
    public DirectExchange delayPluginExchange(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-delayed-message", "direct");

        DirectExchange directExchange = new DirectExchange("delay_plugin_exchange",true,false,arguments);
        directExchange.setDelayed(true);

        return directExchange;
    }

    @Bean
    public Queue delayPluginQueue(){
        return new Queue("delay_plugin_queue");
    }

    @Bean
    public Binding delayPluginBinding(){
        return BindingBuilder.bind(delayPluginQueue()).to(delayPluginExchange()).with("testPluginDelay");
    }

}
