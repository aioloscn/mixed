package com.aiolos.threadcoreknowledge.test;

/**
 * @author Aiolos
 * @date 2019-10-15 14:17
 */
public class Deadlock implements Runnable {

    private int flag;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Deadlock d1 = new Deadlock();
        Deadlock d2 = new Deadlock();
        d1.flag = 0;
        d2.flag = 1;
        Thread t1 = new Thread(d1, "thread1");
        Thread t2 = new Thread(d2, "thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("finished");
    }

    @Override
    public void run() {

        if (flag == 0) {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                }
            }
        }
        if (flag == 1) {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                }
            }
        }
    }
}
