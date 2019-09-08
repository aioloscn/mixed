package com.aiolos.threadcoreknowledge.singleton;

/**
 * 饿汉式（静态常量）（可用）
 * @author Aiolos
 * @date 2019-09-08 20:21
 */
public class Singleton1 {

    // 类加载的时候就实例化完毕，虚拟机自身保证线程安全
    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
