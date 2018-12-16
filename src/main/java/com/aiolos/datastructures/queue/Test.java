package com.aiolos.datastructures.queue;

/**
 * Created by aiolos on 2018-11-17.
 */
public class Test {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue();
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

        LoopQueue loopQueue = new LoopQueue();
        for (int i = 0; i < 10; i ++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);

            if (i % 3 == 2) {
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }

        LinkedListQueue linkedListQueue = new LinkedListQueue();
        for (int i = 0; i < 10; i ++) {
            linkedListQueue.enqueue(i);
            System.out.println(linkedListQueue);

            if (i % 3 == 2) {
                linkedListQueue.dequeue();
                System.out.println(linkedListQueue);
            }
        }
    }
}
