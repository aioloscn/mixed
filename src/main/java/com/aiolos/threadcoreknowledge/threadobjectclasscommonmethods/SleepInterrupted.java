package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每隔1秒输出当前时间，被中断后观察
 * @author Aiolos
 * @date 2019-08-30 15:55
 */
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                // 被中断抛出异常，立马继续执行而不再休眠
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("被中断");
                e.printStackTrace();
            }
        }
    }
}
