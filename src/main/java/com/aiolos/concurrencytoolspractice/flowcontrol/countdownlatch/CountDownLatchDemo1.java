package com.aiolos.concurrencytoolspractice.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 工厂质检，5个工人检查，所有人都认为通过，才通过
 * 一个线程去等待多个线程都执行完毕，再继续自己的工作
 * @author Aiolos
 * @date 2021/7/13 6:36 下午
 */
public class CountDownLatchDemo1 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + "完成了检查");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            executor.submit(runnable);
        }

        System.out.println("等待5个人检查完成...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有人都完成了工作");
    }
}
