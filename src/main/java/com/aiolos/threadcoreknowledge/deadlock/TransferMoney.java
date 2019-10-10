package com.aiolos.threadcoreknowledge.deadlock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 互相转账必然死锁的情况，以及避免死锁的修复
 * @author Aiolos
 * @date 2019-10-09 22:35
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account("a", 500);
    static Account b = new Account("b", 500);
    static Object lock = new Object();
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额：" + a.balance);
        System.out.println("b的余额：" + b.balance);
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

        /**
         * 修复死锁
         */
        class Helper {
            public void transfer() {
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

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Thread: " + Thread.currentThread().getName() + ", from: " + fromHash + ", to: " + toHash);

        if (fromHash < toHash) {
            synchronized (from) {
                try {
                    System.out.println("0拿到" + from.name + "锁，准备拿" + to.name + "锁");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                try {
                    System.out.println("1拿到" + to.name + "锁，准备拿" + from.name + "锁");
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (lock) {
                new Helper().transfer();
            }
        }

        /*synchronized (from) {
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
        }*/
    }

    static class Account {

        String name;
        int balance;

        public Account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }
    }
}
