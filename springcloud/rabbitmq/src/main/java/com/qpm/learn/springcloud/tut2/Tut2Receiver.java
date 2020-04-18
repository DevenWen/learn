package com.qpm.learn.springcloud.tut2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
@Slf4j
public class Tut2Receiver {

    private final int instance;

    public Tut2Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("ThreadId:{} instance {} [x] received {}", Thread.currentThread().getId(), this.instance, in);
        doWork(in);
        watch.stop();
        log.info("ThreadId:{} instance {} [x] received Done {}s", Thread.currentThread().getId(), this.instance, watch.getTotalTimeSeconds());
    }

    private void doWork(String in ) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(100);
            }
        }
    }

}
