package com.aiolos.threadcoreknowledge.stopthreads;

/**
 * run方法内没有sleep或wait方法时，停止线程
 * @author Aiolos
 * @date 2019-08-28 20:50
 */
public class RightWayStopThreadWithoutSleep implements Runnable {


    @Override
    public void run() {

        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num ++;
        }
        System.out.println("Task End");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
