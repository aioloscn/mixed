package com.aiolos.concurrencytoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池来执行，new了1000次SimpleDateFormat-运行-销毁开销太大
 * @author Aiolos
 * @date 2020-01-02 13:28
 */
public class ThreadLocalNormalUsage2 {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private String date(int seconds) {

        // 从1970-01-01 00:00:00到现在的毫秒数
        Date date = new Date(seconds * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage2().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
}
