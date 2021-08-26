package com.aiolos.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.LinkedList;
import java.util.Date;

/**
 * 用wait/notify实现生产者消费者模式
 * 因为用的是同一个storage，又被synchronized修饰，所以不能同时既生产又消费，但可以交替执行
 * @author Aiolos
 * @date 2019-08-30 10:42
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {

        // storage中的wait()、notify()用的对象锁this指storage这个实例，对象可能被多次实例化，必须是同一个实例才是共用同一把锁
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class EventStorage {

    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put() {

        System.out.println("进入put()");
        try {
            while (storage.size() == maxSize) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void take() {

        System.out.println("进入take()");
        try {
            while (storage.size() == 0) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("拿到了" + storage.poll() + "，现在仓库还剩下" + storage.size());
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
