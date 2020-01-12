package com.aiolos.concurrencytoolspractice.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aiolos
 * @date 2020-01-12 18:01
 */
public class LockInterruptibly implements Runnable {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread t1 = new Thread(lockInterruptibly);
        Thread t2 = new Thread(lockInterruptibly);
        t1.start();
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获得锁期间被中断");
        }
    }
}
