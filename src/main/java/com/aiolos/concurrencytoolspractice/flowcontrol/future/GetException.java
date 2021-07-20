package com.aiolos.concurrencytoolspractice.flowcontrol.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 演示get过程中抛出异常，for循环是为了演示抛出Exception的时机：并不是一产生异常就抛出，而是直到get的时候，才会抛出
 * @author Aiolos
 * @date 2021/7/20 4:12 下午
 */
public class GetException {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        List<Future> futures = new ArrayList<>();
        Callable<Integer> callable = () -> {
            throw new IllegalArgumentException("Callable抛出异常");
        };
        Future<Integer> future = executor.submit(callable);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            // 此时callable内部已经发生异常了，所以任务已经完成了，以什么样的方式完成并不关心
            System.out.println(future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        }
        executor.shutdown();
    }
}
