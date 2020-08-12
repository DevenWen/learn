package com.qpm.learn.camel;


import com.qpm.learn.ftp.FTPClientTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.apache.camel.component.file.remote.RemoteFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;

/**
 * @author kangqiang.w
 * @Date 2020/8/6
 **/
@Slf4j
@Component
public class FileProcessor implements Processor {


    @Autowired
    FTPClientTemplate ftpTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("processing {}", exchange.getIn());
        GenericFileMessage<RemoteFile> file = (GenericFileMessage<RemoteFile>) exchange.getIn();
        ftpTemplate.deleteFtpServerFile(file.getGenericFile().getAbsoluteFilePath());
        log.info("delete {}", exchange.getIn());
    }
}
