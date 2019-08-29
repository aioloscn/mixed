package com.aiolos.threadcoreknowledge.sixstates;

/**
 * 展示BLOCKED、WAITING、TIMED_WAITING三种状态
 * @author Aiolos
 * @date 2019-08-29 16:45
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {

        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 拿到锁并sleep，状态为TIMED_WAITING
        System.out.println("thread1: " + thread1.getState());
        // 因为拿不到锁，状态为BLOCKED
        System.out.println("thread2: " + thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 状态为WAITING
        System.out.println("thread1: " + thread1.getState());
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
