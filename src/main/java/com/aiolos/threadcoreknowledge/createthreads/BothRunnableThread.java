package com.aiolos.threadcoreknowledge.createthreads;

/**
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
                System.out.println("Thread的run方法");
            }
        }.start();
    }
}
