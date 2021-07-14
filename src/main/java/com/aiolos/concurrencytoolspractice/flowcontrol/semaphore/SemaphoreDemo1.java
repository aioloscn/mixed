package com.aiolos.concurrencytoolspractice.flowcontrol.semaphore;

import java.util.concurrent.*;

/**
 * @author Aiolos
 * @date 2021/7/14 8:27 下午
 */
public class SemaphoreDemo1 {
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(20, 20, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 30; i++) {
            service.submit(new Task());
        }
        // 关闭线程池
        service.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release(2);
        }
    }
}
