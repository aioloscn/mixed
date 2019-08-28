package com.aiolos.threadcoreknowledge.stopthreads;

/**
 * 如果try/catch在while里面，会导致中断失效
 * @author Aiolos
 * @date 2019-08-28 23:09
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num ++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
