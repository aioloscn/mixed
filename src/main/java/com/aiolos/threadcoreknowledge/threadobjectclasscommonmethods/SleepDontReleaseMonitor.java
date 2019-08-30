package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示线程sleep的时候不释放synchronized的monitor，等sleep时间到了以后，正常结束后才释放锁
 * @author Aiolos
 * @date 2019-08-30 15:32
 */
public class SleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {

        SleepDontReleaseMonitor s = new SleepDontReleaseMonitor();
        new Thread(s).start();
        new Thread(s).start();
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了monitor");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "退出了同步代码块");
    }
}
