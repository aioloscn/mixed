package com.aiolos.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不适用情况2
 * @author Aiolos
 * @date 2019-09-07 18:30
 */
public class NoVolatile2 implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new NoVolatile2();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoVolatile2) r).done);
        System.out.println(((NoVolatile2) r).realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            flipDone();
            realA.incrementAndGet();
        }
    }

    /**
     * done取决于之前的状态，是线程不安全的
     */
    private void flipDone() {
        done = !done;
    }
}
