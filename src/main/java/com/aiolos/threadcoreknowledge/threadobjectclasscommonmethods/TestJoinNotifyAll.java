package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示Thread类实例去执行wait()，当run方法执行完毕后会隐式的调用notifyAll()方法
 * @author Aiolos
 * @date 2021/8/26 6:49 下午
 */
public class TestJoinNotifyAll {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "执行完了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        new Thread(() -> {
            synchronized (thread) {
                System.out.println(Thread.currentThread().getName() + "竞争到锁");
                try {
                    thread.wait();
                    System.out.println(Thread.currentThread().getName() + "被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (thread) {
                System.out.println(Thread.currentThread().getName() + "竞争到锁");
                try {
                    thread.wait();
                    System.out.println(Thread.currentThread().getName() + "被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        thread.start();
    }
}
