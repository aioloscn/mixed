package com.aiolos.threadcoreknowledge.singleton;

/**
 * 懒汉式（线程不安全）（不推荐）
 * @author Aiolos
 * @date 2019-09-08 20:35
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
