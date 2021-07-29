package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * join的代替写法，分析join原理
 * @author Aiolos
 * @date 2019-08-30 20:47
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        thread.start();
        System.out.println("开始等待子线程运行完毕");
        Thread.sleep(1000);
//        thread.join();
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("所有子线程执行完毕");
    }
}
