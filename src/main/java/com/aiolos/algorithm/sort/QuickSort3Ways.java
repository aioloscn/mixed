package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-25 19:11
 */
public class QuickSort3Ways {

    private QuickSort3Ways() {}

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {

        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        int lt = l; // arr[l + 1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1;  // arr[lt + 1...i) == v
        while (i < gt) {

            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1); // 将==v里面的第一个元素和<v的元素交换位置
                lt++;
                i++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }

        // 此时lt指向的<v里面最后一个位置，和v交换位置
        swap(arr, l, lt);
        // 上面交换后lt指向的是==v里面的第一个元素位置，所以要-1
        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }

    private static void swap(Object[] arr, int i, int j) {

        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = SortHelper.generateRandomArray(n, 0, 100);
        SortHelper.testSort("com.aiolos.algorithm.sort.QuickSort3Ways", arr);
        Integer[] arr2 = SortHelper.generateRandomArray(n, 0, 1000000);
        SortHelper.testSort("com.aiolos.algorithm.sort.QuickSort3Ways", arr2);
    }
}
