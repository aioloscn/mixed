package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3个线程，线程1和线程2首先被阻塞，线程3去唤醒，notify()和notifyAll()的区别
 * start先执行不代表线程先启动
 * @author Aiolos
 * @date 2019-08-29 20:38
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + " waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + " is waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    System.out.println("ThreadC notified.");
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(200); // 注释掉可能会先与其他线程启动，导致让其他线程一直在等待锁
        threadC.start();
    }
}
