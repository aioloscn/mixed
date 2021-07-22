package com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable;

import java.io.IOException;

/**
 * @author Aiolos
 * @date 2021/7/21 6:46 下午
 */
public class MayFail implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5)
            throw new IOException(Thread.currentThread().getName() + "读取文件出错");
        Thread.sleep(2000);
        return Integer.valueOf(arg);
    }
}
