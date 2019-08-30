package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0-100的奇偶数，用wait和notify优化PrintOddEvenSync
 * @author Aiolos
 * @date 2019-08-30 13:19
 */
public class WaitNotifyPrintOddEvenSync {

    private static final Object lock = new Object();
    private static int count;

    // 拿到锁就打印
    // 打印完唤醒其他线程，自己休眠
    public static void main(String[] args) {

        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();
    }

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count ++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            // 如果任务还没有完成，释放锁并且自己休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
