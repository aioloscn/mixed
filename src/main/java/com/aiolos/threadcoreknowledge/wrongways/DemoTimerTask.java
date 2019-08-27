package com.aiolos.threadcoreknowledge.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 用定时器创建线程
 * @author Aiolos
 * @date 2019-08-27 23:52
 */
public class DemoTimerTask {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
