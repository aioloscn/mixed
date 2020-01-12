package com.aiolos.concurrencytoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池来执行，不同线程使用同一个非线程安全的工具出现线程安全问题
 * @author Aiolos
 * @date 2020-01-02 13:28
 */
public class ThreadLocalNormalUsage3 {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private String date(int seconds) {

        // 从1970-01-01 00:00:00到现在的毫秒数
        Date date = new Date(seconds * 1000);
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage3().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
}
