package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 展示wait和notify的基本用法
 * 1. 研究代码的执行顺序
 * 2. 证明wait是释放锁的
 * @author Aiolos
 * @date 2019-08-29 20:04
 */
public class WaitNotify {

    public static Object object = new Object();

    static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行了");
                try {
                    // 等待并释放锁
                    object.wait();
                    // 等待期间如果被中断就会抛出异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // object.wait()释放锁之后需要竞争到锁才会继续执行下面的代码
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    /**
     * 线程2在线程1陷入阻塞的过程中把它唤醒
     */
    static class Thread2 extends Thread {

        @Override
        public void run() {
            // 根据代码执行顺序，这里将等待锁，直到其他线程执行了object.wait()释放锁
            synchronized (object) {
                object.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }
}
