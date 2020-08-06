package com.qpm.learn.camel;


import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;

/**
 * @author kangqiang.w
 * @Date 2020/8/6
 **/
@Slf4j
@Component
public class FileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("processing {}", exchange.getIn());
    }
}
