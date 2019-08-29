package com.aiolos.threadcoreknowledge.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示用volatile的局限：part2
 * 陷入阻塞时，volatile是无法停止线程的
 * 生产者的生产速度很快，消费者的消费速度很慢，阻塞队列满了以后生产者会阻塞，等待消费者进一步消费
 * @author Aiolos
 * @date 2019-08-29 10:34
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {

        // 队列满了的时候放不进去，空的时候取也会阻塞
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNum()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要数据了");
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

class Producer implements Runnable {

    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {

        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "放到队列中");
                    storage.put(num);
                }
                num ++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNum() {

        if (Math.random() > 0.95)
            return false;
        return true;
    }
}
