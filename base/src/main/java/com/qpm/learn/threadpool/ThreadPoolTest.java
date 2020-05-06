package com.qpm.learn.threadpool;

import java.util.concurrent.*;

/**
 * 线程池延时执行任务，任务有异常时，线程池会吞掉异常堆栈，并且不继续执行后续的任务。
 */
public class ThreadPoolTest {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    static final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            t.printStackTrace();
        }
    };

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("exec exception Task");
                throw new RuntimeException("test run exception");
            }
        });


        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("exec exception Schedule Task");
                throw new RuntimeException("test run exception Schedule");
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("exec exception Thread Task");
                throw new RuntimeException("test run exception Thread");
            }
        }).start();
    }



}
