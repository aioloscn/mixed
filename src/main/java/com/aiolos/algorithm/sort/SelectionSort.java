package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-21 15:09
 */
public class SelectionSort {

    private SelectionSort() {}

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int n = 10000;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 99999);
        SortHelper.testSort("com.aiolos.algorithm.sort.SelectionSort", arr);
        SortHelper.printArray(arr);
    }
}
