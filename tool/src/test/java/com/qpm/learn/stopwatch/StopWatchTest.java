package com.qpm.learn.stopwatch;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author kangqiang.w
 * @Date 2020/7/29
 **/
@RunWith(SpringRunner.class)
@Slf4j
public class StopWatchTest {


    /**
     * 秒表测试
     *
     * 测试 Spring StopWatch
     * @throws Exception
     */
    @Test
    public void testStopWatchSpring() throws Exception{
        StopWatch stopWatch = new StopWatch("watchTest");
        stopWatch.start("step 1");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("step 2");
        Thread.sleep(500);
        stopWatch.stop();
        System.out.println((stopWatch.prettyPrint()));
        System.out.println(stopWatch.toString());
    }

    /**
     * Guava 的 stopwatch，不像 Spring那么好看，可以多阶段的整合；
     * @throws Exception
     */
    @Test
    public void testStopWatchGuava() throws Exception {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        Thread.sleep(1000);
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }
}
