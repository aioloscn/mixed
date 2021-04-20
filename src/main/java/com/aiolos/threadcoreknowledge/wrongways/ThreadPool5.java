package com.aiolos.threadcoreknowledge.wrongways;

import java.util.concurrent.*;

/**
 * 用线程池创建线程——线程池不是一种本质的创建线程的方法，还是new Thread
 * @author Aiolos
 * @date 2019-08-27 23:11
 */
public class ThreadPool5 {

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();

        // 核心线程数
        int corePoolSize = 5;

        // 最大线程数
        int maximumPoolSize = 10;

        // 超过 corePoolSize 线程数量的线程最大空闲时间
        long keepAliveTime = 2;

        // 以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;

        // 创建工作队列，用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor threadPoolExecutor = null;

        try {
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        for (int i = 0; i < 12; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
//            executorService.submit(new Task());
        }
    }
}
