package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aiolos
 * @date 2019-08-30 15:43
 */
public class SleepDontReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(1000);
            System.out.println("线程" + Thread.currentThread().getName() + "已经唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        SleepDontReleaseLock s = new SleepDontReleaseLock();
        new Thread(s).start();
        new Thread(s).start();
    }
}
