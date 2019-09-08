package com.aiolos.threadcoreknowledge.singleton;

/**
 * 饿汉式（静态代码块）（可用）
 * @author Aiolos
 * @date 2019-09-08 20:23
 */
public class Singleton2 {

    private static final Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
