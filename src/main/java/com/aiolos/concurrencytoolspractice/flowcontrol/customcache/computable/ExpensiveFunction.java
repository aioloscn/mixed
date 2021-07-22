package com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable;

/**
 * 耗时计算的实现类，实现了Computable接口，但本身不具备缓存功能，也不需要考虑缓存的事
 * @author Aiolos
 * @date 2021/7/21 1:24 下午
 */
public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
