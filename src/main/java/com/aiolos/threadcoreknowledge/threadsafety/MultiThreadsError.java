package com.aiolos.threadcoreknowledge.threadsafety;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 运行结果出错，计数不准确（减少），找出具体出错位置
 * @author Aiolos
 * @date 2019-09-01 14:52
 */
public class MultiThreadsError implements Runnable {

    static MultiThreadsError instance = new MultiThreadsError();
    final boolean[] marked = new boolean[1000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    int index = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("index: " + instance.index);
        System.out.println("realIndex: " + realIndex);
        System.out.println("wrongCount: " + wrongCount);
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index ++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                // 线程2判断index1是否为true的时候切换到了线程1，index++ = 2，此时线程2判断的是index2是否为true
                if (marked[index] && marked[index - 1]) {
                    System.out.println(index + "发生了冲突");
                    wrongCount.incrementAndGet();
                }
                // 线程1执行到这正要赋值index1的时候切换到了线程2，index++ = 2,此时线程1将index2赋值为true了
                marked[index] = true;
            }
        }
    }
}
