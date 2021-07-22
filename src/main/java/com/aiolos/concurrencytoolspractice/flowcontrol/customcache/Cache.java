package com.aiolos.concurrencytoolspractice.flowcontrol.customcache;

import com.aiolos.concurrencytoolspractice.flowcontrol.customcache.computable.Computable;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 用装饰者模式，给计算器自动添加缓存功能
 * @author Aiolos
 * @date 2021/7/21 1:27 下午
 */
public class Cache<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    private final Computable<A, V> c;

    public Cache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) {
        for (;;) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(callable);
                // 在计算之前就把futureTask放入到缓存里
                // 用putIfAbsent替换put，putIfAbsent是原子性的更新操作，第一个线程cas成功返回true，第二个线程cas失败返回false
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    // 执行Callable中的任务，计算之后future就能get到结果
                    ft.run();
                    System.out.println(Thread.currentThread().getName() + "从FutureTask调用了计算函数");
                }
            }
            // 如果有arg相同的任务在计算，那么线程会在这里阻塞，另一个线程执行到ft.run()后Future里就有值了，这里就会解除阻塞获得结果
            try {
                return f.get();
            } catch (CancellationException e) {
                System.out.println(Thread.currentThread().getName() + "被取消了");
                cache.remove(arg);
            } catch (InterruptedException e) {
                cache.remove(arg);
            } catch (ExecutionException e) {
                System.out.println(Thread.currentThread().getName() + "计算错误，重试");
                // 如果不删除，再次调用依然是之前发生错误的值，但是会导致重复计算的风险
//                cache.remove(arg);
                // 不直接删除发生了错误的futureTask，而是重新构建一个并替换cache中的ft
                FutureTask newFt = new FutureTask<>(() -> c.compute(arg));
                if (cache.replace(arg, f, newFt)) {
                    newFt.run();
                }
            }
        }
    }

    public V compute(A arg, long expire) {
        if (expire > 0) {
            executor.schedule(() -> {
                expire(arg);
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    private synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            if (future.isDone()) {
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清楚");
            cache.remove(key);
        }
    }
}
