package com.qpm.learn.springcloud.tut2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Tut2Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;


    AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);


    @Scheduled(fixedDelay = 100, initialDelay = 30)
    public void send() {
        StringBuilder builder = new StringBuilder("hello");
        if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            builder.append(".");
        }

        builder.append(count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(queue.getName(), message);
        log.info("[x] send {}", message);
    }

}
