package com.aiolos.concurrencytoolspractice.flowcontrol.future;

import java.util.concurrent.*;

/**
 * @author Aiolos
 * @date 2021/7/20 6:14 下午
 */
public class TimeoutAndCancel {

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
            }
            return new Ad("携程旅行");
        }
    }

    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");
    private static final ExecutorService service = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public void printAd() {
        Future<Ad> future = service.submit(new FetchAdTask());
        Ad ad = DEFAULT_AD;
        try {
            ad = future.get(1990, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        TimeoutAndCancel timeoutAndCancel = new TimeoutAndCancel();
        timeoutAndCancel.printAd();
    }
}
