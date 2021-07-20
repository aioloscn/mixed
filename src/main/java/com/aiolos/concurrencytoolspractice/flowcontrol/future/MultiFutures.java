package com.aiolos.concurrencytoolspractice.flowcontrol.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示批量提交任务时，用List来批量接收结果
 * @author Aiolos
 * @date 2021/7/20 3:40 下午
 */
public class MultiFutures {
    public static void main(String[] args) {
        // 线程数大于corePoolSize并且等待队列已满，但还没有到达最大线程数maximumPoolSize，则线程池会创建新的"非核心线程"来执行任务；
        // maximumPoolSize=10包含了corePoolSize的数量，如果小于10则executor.submit因为等待队列已满提交不了到队列，又超出了最大线程数量而报错
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

        // 由于核心线程数为0，所以只会创建一个非核心线程，从workQueue中取任务执行
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        List<Future> futures = new ArrayList<>();
        Callable<Integer> callable = () -> {
            Thread.sleep(2000);
            return new Random().nextInt();
        };
        for (int i = 0; i < 20; i++) {
            // submit后就会去获取结果
            Future<Integer> future = executor.submit(callable);
            futures.add(future);
        }

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                // i=0时get会等待2s获取到数据，然后进入到下一次循环，会立即获得，因为两个线程是并行工作的，已经get到数据了
                // 前一个子线程阻塞住等待结果，即使后一个子线程已经执行完任务了，也要等前一个子线程执行完毕
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
