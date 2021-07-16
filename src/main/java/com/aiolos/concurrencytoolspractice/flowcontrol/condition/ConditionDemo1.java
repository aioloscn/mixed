package com.aiolos.concurrencytoolspractice.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aiolos
 * @date 2021/7/14 11:08 下午
 */
public class ConditionDemo1 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("条件不满足，开始等待");
            condition.await();
            System.out.println("条件满足，开始执行后续任务");
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        // method1 await()后会释放锁
        lock.lock();
        try {
            System.out.println("准备工作完成，唤醒其他的线程");
            condition.signal();
            Thread.sleep(1000);
            System.out.println("手动释放锁，被唤醒的线程才能拿到锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 demo = new ConditionDemo1();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                demo.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        demo.method1();
    }
}
