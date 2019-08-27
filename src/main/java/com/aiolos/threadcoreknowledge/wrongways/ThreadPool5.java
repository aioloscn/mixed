package com.aiolos.threadcoreknowledge.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
}
