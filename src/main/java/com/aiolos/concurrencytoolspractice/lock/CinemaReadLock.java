package com.aiolos.concurrencytoolspractice.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁及非公平读写锁的插队策略
 * @author Aiolos
 * @date 2021/4/30 6:51 下午
 */
public class CinemaReadLock {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread0").start();
        new Thread(() -> read(), "Thread1").start();
        new Thread(() -> read(), "Thread2").start();
        new Thread(() -> write(), "Thread3").start();
        /**
         * 默认非公平锁想获取读锁可以插队，但是等待队列中头部是想获取写锁的线程时不能插队
         */
        new Thread(() -> read(), "Thread4").start();
        /**
         * 模拟其他线程正在读取，等待队列头部刚好不是想获取写锁的线程，子线程插队
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread[] = new Thread[1000];
                for (int i = 0; i < thread.length; i++) {
                    thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
                }
                for (int i = 0; i < thread.length; i++) {
                    thread[i].start();
                }
            }
        }).start();
    }
}
