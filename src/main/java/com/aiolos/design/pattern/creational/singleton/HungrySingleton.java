package com.aiolos.design.pattern.creational.singleton;

import java.io.Serializable;

/**
 * @author aiolos
 * 2018-11-09
 */
public class HungrySingleton implements Serializable, Cloneable {
    private HungrySingleton() {
        if (hungrySingleton != null) {
            throw new RuntimeException("The singleton prohibits reflection calls");
        }
    }

    private final static HungrySingleton hungrySingleton;

    static {
        hungrySingleton = new HungrySingleton();
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    private Object readResolve() {
        return hungrySingleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
    }
}
