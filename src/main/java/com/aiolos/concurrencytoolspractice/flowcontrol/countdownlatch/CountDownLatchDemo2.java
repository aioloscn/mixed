package com.aiolos.concurrencytoolspractice.flowcontrol.countdownlatch;

import java.util.concurrent.*;

/**
 * 模拟100米跑，5个运动员等待命令，同时开始跑
 * 第二个CountDownLatch等待5个运动员都到后结束
 * @author Aiolos
 * @date 2021/7/14 7:51 上午
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("No." + no + "开始跑步了");
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                        System.out.println("No." + no + "到达终点");
                    }
                }
            };
            executor.submit(runnable);
        }

        Thread.sleep(3000);
        System.out.println("比赛开始");
        begin.countDown();
        end.await();
        System.out.println("所有人都到达终点");
    }
}
