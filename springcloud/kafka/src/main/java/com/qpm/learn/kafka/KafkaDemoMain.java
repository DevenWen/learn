package com.qpm.learn.kafka;

import com.qpm.learn.kafka.provider.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kangqiang.w@gmail.com
 * @Date 2020-06-05
 **/

@SpringBootApplication
public class KafkaDemoMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaDemoMain.class, args);
        KafkaSender sender = context.getBean(KafkaSender.class);
        for (int i = 0; i < 3; i++) {
            sender.log4jSend();
        }
    }

}
