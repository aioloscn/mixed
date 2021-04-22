package com.aiolos.concurrencytoolspractice.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2021/4/22 12:01 下午
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 5; i++) {
            // 等待5秒后才开始执行任务
//            scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        }

        // 第一次等待1秒后开始任务，之后每隔3秒执行一次任务，不会停止
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
