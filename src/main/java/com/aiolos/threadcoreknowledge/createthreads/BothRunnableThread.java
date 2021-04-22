package com.aiolos.threadcoreknowledge.createthreads;

/**
 * 同时用两种方法启动线程
 * @author Aiolos
 * @date 2019-08-27 19:31
 */
public class BothRunnableThread extends Thread {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable的run方法");
            }
        }) {
            @Override
            public void run() {
                // super.run()方法会调用（Runnable）target.run()方法，不写就只执行重写的run方法
//                super.run();
                System.out.println("Thread的run方法");
            }
        }.start();
    }
}
