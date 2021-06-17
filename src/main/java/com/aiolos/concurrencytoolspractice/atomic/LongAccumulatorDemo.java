package com.aiolos.concurrencytoolspractice.atomic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author Aiolos
 * @date 2021/6/15 11:27 下午
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
        /**
         * 初始值：100，第一次计算：x=100+1+y，y=x=101
         * 第二次计算：x=2+y=2+101=103，y=103
         */
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        IntStream.range(1, 10).forEach(i -> executor.execute(() -> accumulator.accumulate(i)));
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println(accumulator.getThenReset());
    }
}
