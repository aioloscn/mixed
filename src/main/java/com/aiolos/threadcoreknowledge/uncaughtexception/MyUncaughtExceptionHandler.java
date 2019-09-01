package com.aiolos.threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aiolos
 * @date 2019-08-31 22:55
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, t.getName() + "线程异常", e);
        System.out.println(name + "捕获了异常" + t.getName() + "异常" + e);
    }
}
