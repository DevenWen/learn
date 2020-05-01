package com.qpm.learn.simpletest;

import javafx.scene.paint.Stop;
import junit.framework.TestCase;
import junit.runner.BaseTestRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 加权轮询的测试
 *
 * @Author kangqiang.w
 * @Date 2020/4/25
 */
@Slf4j
public class WeightedPollingTest extends TestCase {

    /**
     * 带有权重的Server类
     */
    @ToString
    public static class Server {
        private int id;
        /**
         * 权重值
         */
        private int weight;
        /**
         * 权重数组
         */
        private byte[] abandonArray;

        Server(int id, int weight, byte[] abandonArray) {
            this.id = id;
            this.weight = weight;
            this.abandonArray = abandonArray;
        }

        private AtomicInteger timesCount = new AtomicInteger(0);//统计次数
    }

    private Server[] servers;
    private AtomicInteger requestCount = new AtomicInteger();
    private AtomicInteger start = new AtomicInteger();

    /**
     * 统计次数
     */
    private int[] countArray = new int[10];

    // config
    final int[] weightArray = {5,5,5,5,5, 5,5,5,5,5};

    private Random random = new Random();

    /**
     * 按权重初始化10个服务器
     */
    @Before
    public void before() {
        servers = new Server[10];

        for (int i = 0; i < 10; i++) {
            Server server = new Server(i, weightArray[i], randomGenerator(10, 10 - weightArray[i]));
            log.info("create server: {}", server);
            servers[i] = server;
        }
    }

    /**
     * 测试加权轮询
     */
    public void testRun() {
        before();
        log.info("config: {}", weightArray);
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < 1_000_0000; i++) {
            Server server = randomServerByWeight();
            server.timesCount.incrementAndGet();
            countArray[server.id]++;    // 统计落入的Server
        }
        watch.stop();
        log.info("总耗时：{}", watch.getTotalTimeMillis());
        log.info("加权统计: {}", countArray);
        log.info("requestCount: {}", requestCount.get());
    }

    public void testRandomCpu() {
        StopWatch watch = new StopWatch();
        watch.start();
        Random random = new Random();
        for (int i = 0; i < 1_000_0000; i++) {
            random.nextInt();
        }
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
    }


    public Server randomServerByWeight() {
        final int count = 10;
        final int start = this.start.incrementAndGet();
        for (int i = start; i < start + count; i++) {
            int index = i % count;  // 获得主机下标
            Server server = servers[index];
            int requestTime = requestCount.get();
            if (server.weight <= 10 && server.weight > -1) {
                // 权重过滤，按一定的比率放弃请求
                byte[] abandonArray = server.abandonArray;
                if (abandonArray[requestTime % abandonArray.length] == 1) {
                    // 不符合
                    requestCount.getAndIncrement();
                    continue;
                }
            }
            requestCount.getAndIncrement();
            return server;
        }

        throw new RuntimeException("轮询失败，找不到服务器");
    }

    /**
     * 生成加权轮询字符串
     * @param limit 随机数组的位数
     * @param num 随机数组中1的个数，其中1表示抛弃请求
     * @return eg:[0,0,0,0,0 ,1,0,0,0，0] 表示 1%抛弃请求
     */
    private static byte[] randomGenerator(int limit, int num) {
        byte[] tempArray = new byte[limit];
        assert num > 0;
        assert num < limit;

        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int temp = Math.abs(random.nextInt()) % limit;
            while (tempArray[temp] == 1) {
                temp = Math.abs(random.nextInt()) % limit;
            }
            tempArray[temp] = 1;
        }

        return tempArray;
    }
}
