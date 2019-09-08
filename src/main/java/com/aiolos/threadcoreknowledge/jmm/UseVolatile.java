package com.aiolos.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用情况1
 * @author Aiolos
 * @date 2019-09-07 18:24
 */
public class UseVolatile implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new UseVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((UseVolatile) r).done);
        System.out.println(((UseVolatile) r).realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    /**
     * done纯粹赋值是原子操作的
     */
    private void setDone() {
        done = true;
    }
}
