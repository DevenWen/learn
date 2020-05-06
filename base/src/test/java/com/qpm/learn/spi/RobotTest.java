package com.qpm.learn.spi;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ServiceLoader;
import java.util.function.Consumer;

/**
 * SPI 测试
 */
public class RobotTest extends TestCase {

    public void testSayHello() {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

}