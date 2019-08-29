package com.aiolos.threadcoreknowledge.stopthreads;

/**
 * 最佳实践2：在catch子语句中调用Thread.currentThread().interrupt()来恢复设置中断，以便于在后续的执行中，依然能够检查到刚才发生了中断
 * @author Aiolos
 * @date 2019-08-29 9:07
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
