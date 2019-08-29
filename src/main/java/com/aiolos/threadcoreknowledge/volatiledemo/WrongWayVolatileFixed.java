package com.aiolos.threadcoreknowledge.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 修复volatile无法中断线程阻塞的问题
 * @author Aiolos
 * @date 2019-08-29 12:33
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {

        WrongWayVolatileFixed body = new WrongWayVolatileFixed();
        // 队列满了的时候放不进去，空的时候取也会阻塞
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNum()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要数据了");
        producerThread.interrupt();
    }

    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {

            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
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
}
