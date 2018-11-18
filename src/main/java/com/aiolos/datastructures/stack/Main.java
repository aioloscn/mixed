package com.aiolos.datastructures.stack;

import java.util.Random;

/**
 * Created by aiolos on 2018-11-18.
 */
public class Main {
    public static void main(String[] args) {

        int opCount = 1000000;
        ArrayStack arrayStack = new ArrayStack();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + "s");

        LinkedListStack linkedListStack = new LinkedListStack();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + "s");
    }

    private static double testStack(Stack<Integer> s, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            s.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            s.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
