package com.aiolos.threadcoreknowledge.singleton;

/**
 * 静态内部类（可用）
 * @author Aiolos
 * @date 2019-09-08 21:14
 */
public class Singleton7 {

    private Singleton7() {

    }

    /**
     * 内部类不会在JVM启动的时候初始化
     */
    private static class SingletonInstance {
        private static final Singleton7 INSTNACE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTNACE;
    }
}
