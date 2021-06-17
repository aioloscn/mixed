package com.aiolos.concurrencytoolspractice.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Aiolos
 * @date 2021/6/15 5:34 下午
 */
public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] incrementerThreads = new Thread[100];
        Thread[] decrementerThreads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            incrementerThreads[i] = new Thread(incrementer);
            decrementerThreads[i] = new Thread(decrementer);
            incrementerThreads[i].start();
            decrementerThreads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                incrementerThreads[i].join();
                decrementerThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0)
                System.out.println("发现错误：" + i);
        }
        System.out.println("运行结束");
    }
}

class Incrementer implements Runnable {
    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}

class Decrementer implements Runnable {
    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}
