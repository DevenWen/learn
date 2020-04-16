package com.qpm.learn.design.build;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonTest {


    @Test
    public void singletonATest() {
        // 这个方法不会导致内部类加载
        Class clazz = Singleton.class;

        // 这样依然不会被初始化（完成了加载和链接(验证、准备、解析)）
        Class clazzA = Singleton.SingletonA.class;

        // 此处才会触犯类的初始化
        Singleton.SingletonA instance = Singleton.SingletonA.getInstance();
    }

}