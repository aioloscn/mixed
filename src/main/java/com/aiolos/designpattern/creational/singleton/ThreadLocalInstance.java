package com.aiolos.designpattern.creational.singleton;

/**
 * @author aiolos
 * 2018-11-12
 */
public class ThreadLocalInstance {

    private static final ThreadLocal<ThreadLocalInstance> threadLocalInstance = new ThreadLocal<ThreadLocalInstance>() {
        @Override
        protected ThreadLocalInstance initialValue() {
            return new ThreadLocalInstance();
        }
    };

    private ThreadLocalInstance() {}

    public static ThreadLocalInstance getInstance() {
        return threadLocalInstance.get();
    }
}
