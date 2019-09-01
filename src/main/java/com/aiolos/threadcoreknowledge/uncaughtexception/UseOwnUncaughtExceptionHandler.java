package com.aiolos.threadcoreknowledge.uncaughtexception;

/**
 * @author Aiolos
 * @date 2019-08-31 23:00
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("异常捕获器"));
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(1000);
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(1000);
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(1000);
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
