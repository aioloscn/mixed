package com.aiolos.designpattern.creational.singleton;

import java.io.Serializable;

/**
 * Created by aiolos on 2018-11-01.
 */
public class LazyDoubleCheckSingleton implements Serializable {
    // 禁止重排序
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    private LazyDoubleCheckSingleton() {

    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {
                    // 1.分配内存给这个对象
                    // 2.初始化对象
                    // 3.设置lazyDoubleCheckSingleton指向刚分配的内存地址
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }

    private Object readResolve() {
        return lazyDoubleCheckSingleton;
    }
}
