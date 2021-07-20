package com.aiolos.concurrencytoolspractice.flowcontrol.future;

import java.util.concurrent.*;

/**
 * 演示FutureTask的用法
 * @author Aiolos
 * @date 2021/7/20 8:39 下午
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        Task task = new Task();
        // 传入Callable参数
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        // 当作一个Runnable放到线程或线程池中去执行
//        new Thread(futureTask).start();
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        // 执行Callable任务建议用submit，执行Runnable任务用execute
        exec.submit(futureTask);
        try {
            Integer integer = futureTask.get();
            System.out.println("task运行结果：" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(2000);
            int sum = 0;
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
