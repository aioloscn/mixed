package com.aiolos.threadcoreknowledge.singleton;

/**
 * 双重检查锁（可用）(线程安全，延迟加载，效率较高)
 * @author Aiolos
 * @date 2019-09-08 20:41
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null)
                    instance = new Singleton6();
            }
        }
        return instance;
    }
}
