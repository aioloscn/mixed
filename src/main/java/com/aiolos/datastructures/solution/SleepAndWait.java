package com.aiolos.datastructures.solution;

/**
 * @author Aiolos
 * @date 2019-03-11 19:43
 */
public class SleepAndWait {

    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread A get lock");
                        Thread.sleep(20);
                        System.out.println("Thread A do wait method");
                        lock.wait();
                        System.out.println("Thread A is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread B is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread B get lock");
                        Thread.sleep(10);
                        System.out.println("Thread B is done");
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
