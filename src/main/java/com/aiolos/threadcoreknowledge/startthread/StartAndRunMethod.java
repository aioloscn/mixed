package com.aiolos.threadcoreknowledge.startthread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 对比start和run两种启动线程的方式
 * @author Aiolos
 * @date 2019-08-28 12:31
 */
public class StartAndRunMethod {

    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        // run方法是主线程调用一个普通方法
        runnable.run();
        new Thread(runnable).start();
    }
}
