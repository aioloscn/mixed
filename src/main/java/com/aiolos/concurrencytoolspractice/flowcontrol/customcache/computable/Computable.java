package com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable;

/**
 * 有一个计算函数computer，用来代表耗时计算，每个计算器都要实现这个接口，这样就可以无侵入实现缓存功能
 * @author Aiolos
 * @date 2021/7/21 1:21 下午
 */
public interface Computable<A, V> {

    V compute(A arg) throws Exception;
}
