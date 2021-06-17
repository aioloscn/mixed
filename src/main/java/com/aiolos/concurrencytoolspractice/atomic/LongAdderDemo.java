package com.aiolos.concurrencytoolspractice.atomic;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * 演示高并发场景下，LongAdder比AtomicLong性能好
 * @author Aiolos
 * @date 2021/6/15 8:33 下午
 */
public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1000, 1000, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5000));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Task(counter));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        long end = System.currentTimeMillis();
        System.out.println(counter.sum());
        System.out.println("LongAdder耗时：" + (end - start));
    }

    private static class Task implements Runnable {
        private LongAdder counter;

        private Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
