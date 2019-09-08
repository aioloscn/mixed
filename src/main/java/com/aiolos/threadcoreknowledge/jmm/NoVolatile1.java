package com.aiolos.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适合于volatile的场景
 * @author Aiolos
 * @date 2019-09-07 16:38
 */
public class NoVolatile1 implements Runnable {

    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new NoVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoVolatile1) r).a);
        System.out.println(((NoVolatile1) r).realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a ++;
            realA.incrementAndGet();
        }
    }
}
