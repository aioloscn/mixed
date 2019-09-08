package com.aiolos.threadcoreknowledge.singleton;

/**
 * 懒汉式（线程安全）（不推荐使用）
 * @author Aiolos
 * @date 2019-09-08 20:33
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized Singleton4 getInstance() {
        if (instance == null)
            instance = new Singleton4();
        return instance;
    }
}
