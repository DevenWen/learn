package com.qpm.learn.springcloud.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Mq 的接收数据Test
 */
@Slf4j
@Component
public class MqReceiver {

    @RabbitListener(queues = "myQueue")
    public void process(String message) {
        log.info("MyReceiver: {}", message);
    }

}
