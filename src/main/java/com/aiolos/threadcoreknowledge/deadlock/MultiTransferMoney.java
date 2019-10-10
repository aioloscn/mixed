package com.aiolos.threadcoreknowledge.deadlock;

import java.util.Random;

/**
 * @author Aiolos
 * @date 2019-10-10 10:20
 */
public class MultiTransferMoney {

    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 1000000;
    private static int NUM_THREADS = 20;

    public static void main(String[] args) {

        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account("a", NUM_MONEY);
        }

        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = random.nextInt(NUM_ACCOUNTS);
                    int toAccount = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAccount], accounts[toAccount], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
