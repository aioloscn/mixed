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
     * @return  返回j，使得arr[l...j-1] < arr[j] && arr[j] > [j+1...r]
     */
    private static int partition(Comparable[] arr, int l, int r) {

        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        // 取考察数组的第一个元素作为v
        Comparable v = arr[l];
        // 初始时j = l，所以arr[l + 1...j]和arr[j + 1...i)都是空，i表示当前要考察的元素所以是开区间
        int j = l;
        for (int i = l + 1; i <= r; i++) {

            if (arr[i].compareTo(v) < 0) {
                // | v | < v | > v | e |
                //   l      j        i
                // swap
                // | v | < v | e | > v |
                //   l         j         i
                // 将当前考察位置的元素和arr[j]位置的元素交换位置并j++，满足arr[l + 1...j] < v
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
