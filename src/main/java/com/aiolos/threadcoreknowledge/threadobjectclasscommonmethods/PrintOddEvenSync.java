package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0-100的奇偶数，用synchronized关键字实现
 * @author Aiolos
 * @date 2019-08-30 12:47
 */
public class PrintOddEvenSync {

    private static int count;
    private static final Object lock = new Object();

    // 一个线程只处理偶数，一个线程只处理奇数（用位运算）
    // 用synchronized来通信
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        } else {
                            System.out.println(Thread.currentThread().getName() + "竞争到锁但没有操作");
                        }
                    }
                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        } else {
                            System.out.println(Thread.currentThread().getName() + "竞争到锁但没有操作");
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}
