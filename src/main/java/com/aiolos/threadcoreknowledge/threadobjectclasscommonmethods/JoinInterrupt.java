package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 演示join期间被中断的效果
 * @author Aiolos
 * @date 2019-08-30 16:50
 */
public class JoinInterrupt {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("Thread1 finished.");
                } catch (InterruptedException e) {
                    System.out.println("子线程被中断");
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            // 主线程等待子线程过程中抛出了异常，所以是主线程被中断，主线程抛出了异常
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "线程被中断了");
            thread1.interrupt();
            e.printStackTrace();
        }
        System.out.println("子线程运行完毕");
    }
}
