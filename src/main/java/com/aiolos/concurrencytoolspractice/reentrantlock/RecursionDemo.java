package com.aiolos.concurrencytoolspractice.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示可重入锁
 * @author Aiolos
 * @date 2021/4/26 12:09 上午
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        System.out.println("已经对资源进行处理");
        lock.lock();
        try {
            if (lock.getHoldCount() < 5) {
                accessResource();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
