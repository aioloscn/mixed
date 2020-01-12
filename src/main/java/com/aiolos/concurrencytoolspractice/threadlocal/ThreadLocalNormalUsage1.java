package com.aiolos.concurrencytoolspractice.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 30个线程打印日期，创建SimpleDateFormat-运行-销毁开销太大
 * @author Aiolos
 * @date 2020-01-02 13:28
 */
public class ThreadLocalNormalUsage1 {

    private String date(int seconds) {

        // 从1970-01-01 00:00:00到现在的毫秒数
        Date date = new Date(seconds * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage1().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }
    }
}
