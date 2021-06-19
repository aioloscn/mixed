package com.aiolos.concurrencytoolspractice.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 组合操作并不保证线程安全
 * @author Aiolos
 * @date 2021/6/19 8:32 下午
 */
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("aiolos", 0);
        OptionsNotSafe r = new OptionsNotSafe();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            for (; ; ) {
                Integer score = scores.get("aiolos");
                // 这一步线程不安全
                Integer newScore = score + 1;
                // 用replace保证线程安全，但不保证成功，比如cas思想检查score是否和一开始的一样，score和newScore一样也返回false
                boolean b = scores.replace("aiolos", score, newScore);
//                scores.put("aiolos", newScore);
                if (b)  break;
            }
        }
    }
}
