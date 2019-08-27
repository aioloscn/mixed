package com.aiolos.threadcoreknowledge.createthreads;

/**
 * 用Thread方式实现线程
 * @author Aiolos
 * @date 2019-08-27 19:02
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {

        new ThreadStyle().start();
    }
}
