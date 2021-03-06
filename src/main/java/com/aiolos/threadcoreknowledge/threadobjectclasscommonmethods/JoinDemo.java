package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示join，注意语句输出顺序，会变化
 * @author Aiolos
 * @date 2019-08-30 16:41
 */
public class JoinDemo implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        JoinDemo joinDemo = new JoinDemo();
        Thread thread1 = new Thread(joinDemo);
        Thread thread2 = new Thread(joinDemo);

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        thread1.join();
        thread2.join();
        System.out.println("所有子线程执行完毕");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
