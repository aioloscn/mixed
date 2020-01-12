package com.aiolos.designpattern.creational.singleton;

/**
 * @author aiolos
 * 2018-11-09
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
        if (InnerClass.staticInnerClassSingleton != null) {
            throw new RuntimeException("The singleton prohibits reflection calls");
        }
    }

    private static class InnerClass {
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.staticInnerClassSingleton;
    }
}
