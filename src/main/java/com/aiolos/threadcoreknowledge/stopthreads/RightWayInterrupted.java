package com.aiolos.threadcoreknowledge.stopthreads;

/**
 * thread.interrupted()方法的目标对象是“当前线程”，而不管本方法来自于哪个对象
 * @author Aiolos
 * @date 2019-08-29 13:28
 */
public class RightWayInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {

                }
            }
        });

        // 启动线程
        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        // 获取中断标志
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        // 获取中断标志并重置——虽然对象是threadOne，但执行它的线程是主函数，main函数没有被中断，所有返回false
        System.out.println("threadOne.interrupted: " + threadOne.interrupted());
        // 获取中断标志并重置
        System.out.println("Thread.interrupted: " + Thread.interrupted());
        // 获取中断标志
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
//        threadOne.join();
        System.out.println("Main thread is over.");
    }
}
