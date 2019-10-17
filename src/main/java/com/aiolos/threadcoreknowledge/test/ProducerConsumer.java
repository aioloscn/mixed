package com.aiolos.threadcoreknowledge.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Aiolos
 * @date 2019-10-15 14:57
 */
public class ProducerConsumer {

    public static void main(String[] args) {

        Storage storage = new Storage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Storage {

    private int size;
    private List<Integer> storage;
    Random random = new Random();

    public Storage() {
        this.size = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put() {

        System.out.println("进入put()");
        if (storage.size() == size) {
            try {
                System.out.println("仓库已满");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(random.nextInt(10));
        System.out.println("当前仓库库存为" + storage.size());
        notify();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void take() {

        System.out.println("进入take()");
        if (storage.size() == 0) {
            System.out.println("仓库没有库存");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("拿到了%d，现在仓库还剩下%d", storage.remove(0), storage.size());
        System.out.println();
        notify();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        this.storage = storage;
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}
