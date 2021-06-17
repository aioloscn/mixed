package com.aiolos.concurrencytoolspractice.immutable;

/**
 * 演示栈封闭的两种情况
 * 先演示线程争抢带来的错误结果，然后把变量放到方法内，情况就变了
 * @author Aiolos
 * @date 2021/6/17 8:07 下午
 */
public class StackConfinement implements Runnable {

    private int index = 0;

    public void inThread() {
        int neverGoOut = 0;
        for (int i = 0; i < 10000; i++) {
            neverGoOut++;
        }
        System.out.println("栈内保护的数字是线程安全的: " + neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        StackConfinement r = new StackConfinement();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.index);
    }
}
