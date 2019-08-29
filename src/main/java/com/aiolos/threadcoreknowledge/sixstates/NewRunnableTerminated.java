package com.aiolos.threadcoreknowledge.sixstates;

/**
 * 线程的New、Runnable、Terminated状态，即使是正在运行，也是Runnable状态，而不是Running
 * @author Aiolos
 * @date 2019-08-29 16:01
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new NewRunnableTerminated());
        // 打印出NEW的状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
