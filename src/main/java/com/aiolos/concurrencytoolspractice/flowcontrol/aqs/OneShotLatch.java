package com.aiolos.concurrencytoolspractice.flowcontrol.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 利用AQS实现一个简单的线程协作器
 * @author Aiolos
 * @date 2021/7/19 6:29 下午
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败则等待");
                oneShotLatch.await();
                System.out.println(Thread.currentThread().getName() + "继续运行");
            }).start();
        }
        Thread.sleep(3000);
        oneShotLatch.signal();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败则等待");
            oneShotLatch.await();
            System.out.println(Thread.currentThread().getName() + "继续运行");
        }).start();
    }
}
