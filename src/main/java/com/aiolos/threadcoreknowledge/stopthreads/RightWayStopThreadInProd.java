package com.aiolos.threadcoreknowledge.stopthreads;

/**
 * 最佳实践：catch到InterruptedException之后优先选择：在方法签名中抛出异常，那么在run()中语法就会强制try/catch
 * @author Aiolos
 * @date 2019-08-28 23:46
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("go");
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
