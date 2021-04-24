package com.aiolos.concurrencytoolspractice.threadlocal;

/**
 * 演示ThreadLocal空指针问题
 * @author Aiolos
 * @date 2021/4/24 6:14 下午
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        // 如果直接get应该是null而不是空指针异常，所以get方法的返回值也应该是Long，否则在装箱拆箱的过程中会报空指针异常
        System.out.println(threadLocalNPE.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread.start();
    }
}
