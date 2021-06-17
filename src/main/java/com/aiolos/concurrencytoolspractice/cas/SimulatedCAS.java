package com.aiolos.concurrencytoolspractice.cas;

/**
 * 模拟CAS操作，等价代码
 * @author Aiolos
 * @date 2021/6/16 6:09 下午
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }
}
