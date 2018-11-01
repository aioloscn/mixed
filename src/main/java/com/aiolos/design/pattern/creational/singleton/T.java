package com.aiolos.design.pattern.creational.singleton;

/**
 * Created by aiolos on 2018-11-01.
 */
public class T implements Runnable {
    @Override
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " " + lazySingleton);
    }
}
