package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示join，注意语句输出顺序，会变化
 * @author Aiolos
 * @date 2019-08-30 16:41
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {

        JoinDemo joinDemo = new JoinDemo();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        thread1.join();
        System.out.println("thread1执行完毕唤醒主线程，此时才执行thread2.join()，主线程继续阻塞");
        thread2.join();
        System.out.println("所有子线程执行完毕");
    }
}
