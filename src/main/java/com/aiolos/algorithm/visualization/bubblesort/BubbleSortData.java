package com.aiolos.algorithm.visualization.bubblesort;

public class BubbleSortData {

    private int[] numbers;

    public int orderedIndex = -1;           // [0...orderedIndex) 最新一个排好序的索引
    public int currentCompareIndex = -1;    // 当前正在比较的元素的索引

    public BubbleSortData(int N, int randomBound) {

        numbers = new int[N];
        for (int i = 0; i < N; i++)
            numbers[i] = (int)(Math.random() * randomBound) + 1;
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {

        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
