package com.aiolos.concurrencytoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池来执行，
 * 利用ThreadLocal，给每个线程分配自己的SimpleDateFormat对象，保证了线程安全，又高效利用内存
 * @author Aiolos
 * @date 2020-01-02 13:28
 */
public class ThreadLocalNormalUsage5 {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private String date(int seconds) {

        // 从1970-01-01 00:00:00到现在的毫秒数
        Date date = new Date(seconds * 1000);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 保证SimpleDateFormat在每个线程中只有一份，线程池有10个线程就有10个这个对象
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal1.get();
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage5().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
}

class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    // lambda表达式写法
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal1 = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    );
}
