package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * notify选择唤醒的线程是任意的，但是依赖于具体实现的jvm
 * @author Aiolos
 * @date 2021/7/29 3:04 下午
 */
public class NotArbitraryNotify {

    private static List<String> waitList = new LinkedList<>();
    private static List<String> notifyList = new LinkedList<>();

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("线程 [" + threadName + "] 正在等待");
                    waitList.add(threadName);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 【" + threadName + "] 被唤醒了");
                    notifyList.add(threadName);
                }
            }).start();
            // 保证for循环中每次按顺序创建线程
            TimeUnit.MILLISECONDS.sleep(10);
        }

        TimeUnit.MILLISECONDS.sleep(1);

        for (int i = 0; i < 50; i++) {
            synchronized (lock) {
                lock.notify();
                // 被通知的线程进入到等待池
            }
            // 退出synchronized代码块后释放锁，进入下一次循环，synchronized又想获取锁
            // 由于synchronized是非公平锁，竞争成功后导致等待池中的线程被插队乱序，所以notify唤醒看起来是随机的
            // 释放锁后等待1ms保证被唤醒的线程能竞争到锁，此时打印出来的线程是顺序的
            TimeUnit.MILLISECONDS.sleep(1);
        }

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("wait的顺序：" + waitList.toString());
        System.out.println("notify的顺序：" + notifyList.toString());
    }
}
