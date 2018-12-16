package com.aiolos.datastructures.heap;

import java.util.Random;

/**
 * Created by aiolos on 2018-11-24.
 */
public class Test {
    public static void main(String[] args) {

        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        Random random = new Random();
        for (int i = 0; i < n; i ++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = maxHeap.extractMax();
            System.out.println(arr[i]);
        }
    }
}
