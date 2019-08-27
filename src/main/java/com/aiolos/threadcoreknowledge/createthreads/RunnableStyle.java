package com.aiolos.threadcoreknowledge.createthreads;

/**
 * 用Runnable方式创建线程
 * @author Aiolos
 * @date 2019-08-27 18:57
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RunnableStyle());
        thread.start();;
    }
}
