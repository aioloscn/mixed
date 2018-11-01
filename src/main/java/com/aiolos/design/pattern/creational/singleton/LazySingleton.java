package com.aiolos.design.pattern.creational.singleton;

/**
 * Created by aiolos on 2018-11-01.
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private LazySingleton() {

    }
    // 这里锁加在静态方法上相当于锁的这个类的class文件 synchronized (LazySingleton.class) {}
    // 如果锁的不是静态方法相当于锁的是堆内存中生成的对象
    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
