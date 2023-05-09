package com.woniuxy.qiantai.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    private static final String DL_EXCHANGE_NAME = "order_dl_exchange";
    private static final String DL_QUEUE_NAME = "order_dl_queue";

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DL_EXCHANGE_NAME);
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DL_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DL_QUEUE_NAME)
                .build();
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(DL_QUEUE_NAME);
    }
}