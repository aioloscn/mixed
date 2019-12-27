package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-22 22:36
 */
public class QuickSort {

    public static void sort(Comparable[] arr) {

        sort(arr, 0, arr.length - 1);
    }

    /**
     * 递归使用快速排序，对arr[l...r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    private static void sort(Comparable[] arr, int l, int r) {

        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * @param arr
     * @param l
     * @param r
     * @return  返回p，使得arr[l...p-1] < arr[p] && arr[p] > [p+1...r]
     */
    private static int partition(Comparable[] arr, int l, int r) {

        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {

            if (arr[i].compareTo(v) < 0) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(Object[] arr, int i, int j) {

        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, 1000000);
        SortHelper.testSort("com.aiolos.algorithm.sort.QuickSort", arr);
    }
}
