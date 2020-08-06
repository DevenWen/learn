package com.qpm.learn.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqiang.w
 * @Date 2020/8/6
 **/
@Slf4j
@Component
public class DataExtractRoute extends RouteBuilder {

    @Value("${ftp.server.info}")
    private String ftpServer;

    @Value("${ftp.local.dir}")
    private String downloadLocation;

    @Resource
    private FileFilter fileFilter;
    @Resource
    private FileProcessor fileProcessor;

    @Override
    public void configure() throws Exception {
        log.info("[Data Extract Route] start ....");

        from(ftpServer)
                .filter(fileFilter)
                .to(downloadLocation)
                .log(LoggingLevel.INFO, log, "Download file ${file:name} complete")
                .process(fileProcessor);

        log.info("[Data Extract Route] end ....");
    }
}
