package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-21 15:09
 */
public class SelectionSort2 {

    private SelectionSort2() {}

    public static void sort(Comparable[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            // 防止left和right处于中位数时两个位置的值并未排序，但是这时不会再进入循环排序导致数据错误
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0)
                swap(arr, minIndex, maxIndex);

            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0)
                    minIndex = i;
                else if (arr[i].compareTo(arr[maxIndex]) > 0)
                    maxIndex = i;
            }
            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);
            left ++;
            right --;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int n = 11;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 99999);
        SortHelper.testSort("com.aiolos.algorithm.sort.SelectionSort2", arr);
        SortHelper.printArray(arr);
    }
}
