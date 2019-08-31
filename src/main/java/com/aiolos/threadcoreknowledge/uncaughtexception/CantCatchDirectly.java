package com.aiolos.threadcoreknowledge.uncaughtexception;

/**
 * try/catch无法捕获子线程异常
 * try/catch只能捕获对应线程内的异常，在这也就是主线程
 * @author Aiolos
 * @date 2019-08-31 10:49
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        try {
            new Thread(new CantCatchDirectly()).start();
            Thread.sleep(100);
            new Thread(new CantCatchDirectly()).start();
            Thread.sleep(100);
            new Thread(new CantCatchDirectly()).start();
            Thread.sleep(100);
            new Thread(new CantCatchDirectly()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
