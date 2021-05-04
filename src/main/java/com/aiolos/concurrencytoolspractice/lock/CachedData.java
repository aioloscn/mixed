package com.aiolos.concurrencytoolspractice.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁的降级的例子
 * @author Aiolos
 * @date 2021/5/4 4:04 下午
 */
public class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        System.out.println(Thread.currentThread().getName() + "获取了读锁");
        rwl.readLock().lock();
        if (!cacheValid) {
            // 发现缓存失效，就需要重新写入，所以现在需要写锁，由于锁不支持升级，所以在获取锁之前，必须先释放读锁
            System.out.println(Thread.currentThread().getName() + "释放了了读锁");
            rwl.readLock().unlock();
            // 获取写锁
            System.out.println(Thread.currentThread().getName() + "获取了写锁");
            rwl.writeLock().lock();
            try {
                // 这里需要再次判断数据的有效性，因为在我们释放读锁和获取写锁的空隙之间，可能有其他线程修改了数据
                if (!cacheValid) {
                    System.out.println(Thread.currentThread().getName() + "重新写入了缓存");
                    data = new Object();
                    cacheValid = true;
                }
                // 释放了写锁，但依然持有读锁，这样一来，就可以多个线程可以同时读取了，提高了效率
                System.out.println(Thread.currentThread().getName() + "释放了写锁");
                rwl.writeLock().unlock();
                // 在不释放写锁的情况下，直接获取读锁，这就是读写锁的升降级
                read();
            } finally {

            }
        }
    }

    void read() {
        System.out.println(Thread.currentThread().getName() + "获取了读锁");
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取");
            Thread.sleep(2000);
            System.out.println(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 最后释放读锁
            System.out.println(Thread.currentThread().getName() + "最后释放了读锁");
            rwl.readLock().unlock();
        }
    }
}

class ProcessCachedData implements Runnable {

    CachedData cachedData;

    public ProcessCachedData(CachedData cachedData) {
        this.cachedData = cachedData;
    }

    @Override
    public void run() {
        cachedData.processCachedData();
    }
}

class ReadCache implements Runnable {

    CachedData cachedData;

    public ReadCache(CachedData cachedData) {
        this.cachedData = cachedData;
    }

    @Override
    public void run() {
        cachedData.read();
    }
}

class Main {
    public static void main(String[] args) {
        CachedData cachedData = new CachedData();
        new Thread(new ProcessCachedData(cachedData), "Thread0").start();
        new Thread(new ReadCache(cachedData), "Thread1").start();
    }
}