package com.qpm.learn.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Predicate;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kangqiang.w
 * @Date 2020/8/6
 **/
@Component
@Slf4j
public class FileFilter implements Predicate {

    private Map<String, Long> fileName2ModifiedTimeStampMap = new ConcurrentHashMap<>();

    @Override
    public boolean matches(Exchange exchange) {
        // 持久化修改时间
        Message in = exchange.getIn();
        GenericFile genericFile = ((GenericFileMessage) in).getGenericFile();
        long remoteLastModified = genericFile.getLastModified();
        long localLastModified = fileName2ModifiedTimeStampMap.getOrDefault(genericFile.getFileName(), 0L);

        if (remoteLastModified > localLastModified) {
            fileName2ModifiedTimeStampMap.put(genericFile.getFileName(), remoteLastModified);
            log.info("match: {}", genericFile.getFileName());
            return true;
        }
        return false;
    }
}
