package com.aiolos.concurrencytoolspractice.flowcontrol.customcache;

import com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable.ExpensiveFunction;
import com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable.MayFail;

/**
 * @author Aiolos
 * @date 2021/7/21 3:41 下午
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Cache<String, Integer> expensiveComputer = new Cache<String, Integer>(new ExpensiveFunction());
        new Thread(() -> {
                Integer result = expensiveComputer.compute("666", 5000);
                System.out.println("第一个线程计算结果：" + result);
        }).start();


        Thread.sleep(10000);
        new Thread(() -> {
            Integer result = expensiveComputer.compute("666");
            System.out.println("第三个线程计算结果：" + result);
        }).start();
    }
}
