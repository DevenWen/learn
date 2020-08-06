package com.qpm.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@EnableScheduling
@SpringBootApplication
public class DemoApplication {
    public static void main( String[] args ) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("waiting");
    }
}
