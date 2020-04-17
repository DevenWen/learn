package com.qpm.learn.springcloud.config;

import com.qpm.learn.springcloud.message.Tut1Receiver;
import com.qpm.learn.springcloud.message.Tut1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Rabbit mq 官方文档入门
 */
@Profile({"tut1", "hello-world"})
@Configuration
public class QueueConfig {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

}
