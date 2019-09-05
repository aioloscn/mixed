package com.aiolos.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序现象，直到达到某个条件才停止
 * @author Aiolos
 * @date 2019-09-05 12:43
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        for (;;) {

            x = 0;
            y = 0;
            a = 0;
            b = 0;
            ++ i;
            CountDownLatch countDownLatch = new CountDownLatch(1);

            Thread thread1 = new Thread(() -> {
                a = 1;
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = b;
            });

            Thread thread2 = new Thread(() -> {
                b = 1;
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                y = a;
            });

            thread1.start();
            thread2.start();
            countDownLatch.countDown();
            thread1.join();
            thread2.join();
            String result = "第" + i + "次（" + x + ", " + y + "）";
            System.out.println(result);
            if (x == 1 && y == 1)
                break;
        }
    }
}
