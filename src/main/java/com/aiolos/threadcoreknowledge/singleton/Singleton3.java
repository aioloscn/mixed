package com.aiolos.threadcoreknowledge.singleton;

/**
 * 懒汉式（线程不安全）
 * @author Aiolos
 * @date 2019-09-08 20:25
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null)
            instance = new Singleton3();
        return instance;
    }
}
