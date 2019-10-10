package com.aiolos.threadcoreknowledge.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Aiolos
 * @date 2019-10-10 12:40
 */
public class ThreadMXBeanDetection implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {

        ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("a的余额：" + a.balance);
        System.out.println("b的余额：" + b.balance);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockThreads != null && deadlockThreads.length > 0) {

            for (int i = 0; i < deadlockThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockThreads[i]);
                System.out.println(threadInfo.getThreadName());
            }
        }
    }

    @Override
    public void run() {

        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {

        synchronized (from) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance - amount >= 0) {
                    from.balance -= amount;
                    to.balance += amount;
                    System.out.println("成功转账" + amount + "元");
                } else {
                    System.out.println("余额不足，转账失败");
                    return;
                }
            }
        }
    }

    static class Account {

        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }
}
