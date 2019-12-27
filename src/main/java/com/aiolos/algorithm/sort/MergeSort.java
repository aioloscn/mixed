package com.aiolos.algorithm.sort;

import java.util.Arrays;

/**
 * @author Aiolos
 * @date 2019-12-22 21:22
 */
public class MergeSort {

    private MergeSort() {}

    public static void sort(Comparable[] arr) {

        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {

        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int mid = (r - l) / 2 + l;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        // 对于有序的情况不需要归并
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Comparable[] arr, int l, int mid, int r) {

        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // 初始化，i指向左半部分的起始位置l，j指向右半部分的起始位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {

            // 如果左半边部分值偏小导致左半边部分已经全部处理完
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {

        int n = 50000;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 100000);
        SortHelper.testSort("com.aiolos.algorithm.sort.MergeSort", arr);
        SortHelper.printArray(arr);
    }
}
