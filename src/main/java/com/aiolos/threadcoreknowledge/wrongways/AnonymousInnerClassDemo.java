package com.aiolos.threadcoreknowledge.wrongways;

/**
 * 用匿名内部类创建线程的两种方法
 * @author Aiolos
 * @date 2019-08-27 23:59
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        // Lambda
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
