package com.aiolos.algorithm.visualization.mergesort;

public class MergeSortData {

    public int[] numbers;

    public int l, r, mergeIndex;

    public MergeSortData(int N, int randomBound) {

        numbers = new int[N];
        for (int i = 0; i < N; i++)
            numbers[i] = (int)(Math.random() * randomBound) + 1;
    }
}
