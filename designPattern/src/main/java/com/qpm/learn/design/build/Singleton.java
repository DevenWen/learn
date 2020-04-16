package com.qpm.learn.design.build;

import javax.swing.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式
 *  5 种单例的实现方式和区别
 *
 */
public class Singleton {

    /**
     * 饿汉式
     * 在类加载的时候，instance 实例就会被创建并初始化好。所以创建过程是线程安全的。
     *
     * PS: 虚拟机会保证一个类的类构造器<clinit>()在多线程环境中被正确的加锁、同步，
     * 如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的类构造器<clinit>()，
     * 其他线程都需要阻塞等待，直到活动线程执行<clinit>()方法完毕。
     *
     * 1 提前初始化有好有不好
     *
     */
    public static class SingletonA {
        private static final SingletonA instance = new SingletonA();

        public static SingletonA getInstance() {
            return instance;
        }
        private SingletonA(){
            System.out.println("Singleton A init");
        }
    }


    /**
     * 懒汉式
     *
     * 延迟加载。加锁是保证线程安全，但并发量高的时候会引起性能问题，锁假如升级到重锁，
     * 那每次可能都要用到操作系统的调用，发生上下文切换。性能上会成为瓶颈。
     *
     */
    public static class SingletonB {
        private static SingletonB instance;

        public static synchronized SingletonB getInstance() {
            if (instance == null) {
                instance = new SingletonB();
            }
            return instance;
        }

        private SingletonB() {
            System.out.println("Singleton B init");
        }
    }


    /**
     * 双重检测 懒汉式.
     *
     * 延迟加载+并发安全.
     *
     * 存在指令重排序风险
     * 赋值给 instance 和 SingletonC 初始化乱序，导致获得一个未初始化完成的 对象。加 volatile
     * PS: 高版本JDK 已经把 new 操作和初始化操作设置为原子操作了。不会存在重排序问题。
     *
     */
    public static class SingletonC {

        private static volatile SingletonC instance;

        public static SingletonC getInstance() {
            if (instance == null) {
                synchronized (SingletonC.class) {
                    if (instance == null) {
                        instance = new SingletonC();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 静态内部类
     *
     * 利用饿汉式的类加载线程安全，同时又能延迟加载。
     *
     */
    public static class SingletonD {

        private static  class SingletonDHolder {
            private static final SingletonD instance = new SingletonD();
        }

        public static SingletonD getInstance() {
            return SingletonDHolder.instance;   // 访问到这里才会执行类加载
        }

    }

    /**
     * 枚举实现 最简单的实现方式
     * <br>
     *     枚举本身就是一个静态类的语法糖 + 实例化
     */
    public enum  SingletonE {

        INSTANCE;

    }

    /**
     * 单例模式存在的问题：
     * 1、对OOP的特性支持不好
     * 2、隐藏类之间的依赖关系
     * 3、对代码扩展性不好
     * 4、对代码测试性不好
     * 5、不支持有参数构造
     *
     * 解决方法：
     * 1、寻找另外的全局单例解决方案，如：Spring IOC
     */


    /**
     * 单例模式扩展
     * 1、线程单例
     * PS: 线程结束时，如何清除对象？
     */
    public static class SingletonF {
        private static final ConcurrentHashMap<Long, SingletonF> instances = new ConcurrentHashMap<>();

        public static SingletonF getInstance() {
            Long currentThreadId = Thread.currentThread().getId();
            instances.putIfAbsent(currentThreadId, new SingletonF());
            return instances.get(currentThreadId);
        }
    }




}
