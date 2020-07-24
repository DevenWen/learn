package com.qpm.learn.kafka.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qpm.learn.kafka.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author kangqiang.w@gmail.com
 * @Date 2020-06-05
 **/

@Component
@Slf4j
public class KafkaSender {

    private final Logger logSender =  LogManager.getLogger("kafkaLogger");


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());

        log.info("++++++++++ message = {}", gson.toJson(message));
        kafkaTemplate.send("test", message.toString());
    }

    public void log4jSend() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());

        logSender.info(gson.toJson(message));
    }

}
