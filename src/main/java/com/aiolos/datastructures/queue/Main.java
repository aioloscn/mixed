package com.aiolos.datastructures.queue;

import com.aiolos.datastructures.stack.ArrayStack;

import java.util.Random;

/**
 * Created by aiolos on 2018-11-17.
 */
public class Main {
    public static void main(String[] args) {

        int opCount = 100000;
        ArrayQueue arrayQueue = new ArrayQueue();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + "s");

        LoopQueue loopQueue = new LoopQueue();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("ArrayQueue, time: " + time2 + "s");
    }

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            q.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
