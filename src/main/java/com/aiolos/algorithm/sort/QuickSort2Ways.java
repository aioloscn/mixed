package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-25 15:58
 */
@SuppressWarnings("unchecked")
public class QuickSort2Ways {

    private QuickSort2Ways() {}

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
     * @return 返回p，使得arr[l...p-1] < arr[p] && arr[p] > [p+1...r]
     */
    private static int partition(Comparable[] arr, int l, int r) {

        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(v) < 0)
                i++;
            while (j >= l + 1 && arr[j].compareTo(v) > 0)
                j--;
            if (i >= j)
                break;
            swap(arr, i, j);
            i++;
            j--;
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
        Integer[] arr = SortHelper.generateRandomArray(n, 0, 100);
        SortHelper.testSort("com.aiolos.algorithm.sort.QuickSort2Ways", arr);

        Integer[] arr2 = SortHelper.generateRandomArray(n, 0, 1000000);
        SortHelper.testSort("com.aiolos.algorithm.sort.QuickSort2Ways", arr2);
    }
}
