package com.aiolos.concurrencytoolspractice.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2021/4/22 8:13 下午
 */
public class ShutdownThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutdownTask());
        }
        Thread.sleep(2000);
        /*System.out.println(executorService.isShutdown());
        executorService.shutdown();
        // 会抛出异常但之前的任务会继续执行直到完毕
//        executorService.execute(new ShutdownTask());
        // 查看是否进入停止状态了，不是指线程池是否停止了
        System.out.println(executorService.isShutdown());
        Thread.sleep(10000);
        // 查看线程池是否停止了
        System.out.println(executorService.isTerminated());*/

        /*executorService.shutdown();
        // 等待的这10秒内检测线程池是否已经停止
        boolean flag = executorService.awaitTermination(10L, TimeUnit.SECONDS);
        System.out.println(flag);*/

        // 运行中的线程会被中断，workQueue中等待的任务会被返回
        List<Runnable> runnables = executorService.shutdownNow();
    }
}

class ShutdownTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
