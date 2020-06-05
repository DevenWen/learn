package com.qpm.learn.kafka.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author kangqiang.w@gmail.com
 * @Date 2020-06-05
 **/
@Data
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;

}
