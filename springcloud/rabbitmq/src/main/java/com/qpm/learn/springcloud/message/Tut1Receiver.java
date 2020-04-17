package com.qpm.learn.springcloud.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
@Slf4j
public class Tut1Receiver {

    @RabbitHandler
    public void receive(String in) {
        log.info("[x] received {}" + in);
    }

}
