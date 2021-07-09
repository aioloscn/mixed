package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2019-12-21 16:41
 */
@SuppressWarnings("unchecked")
public class InsertionSort {

    public static void sort(Comparable[] arr) {

        // i从第二位开始，向前比较
        for (int i = 1; i < arr.length; i++) {
            // 第一版
            /*for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }*/

            // 第二版
            // 寻找arr[i]合适的插入位置
            Comparable copy = arr[i];
            // j保存元素应该copy应该插入的位置
            int j;
            for (j = i; j > 0 && copy.compareTo(arr[j - 1]) < 0; j--) {
                    arr[j] = arr[j - 1];
            }
            arr[j] = copy;
        }
    }

    public static void sort(Comparable[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            Comparable copy = arr[i];
            int j = i;
            for (; j > l && copy.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = copy;
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
        SortHelper.testSort("com.aiolos.algorithm.sort.InsertionSort", arr);
        SortHelper.printArray(arr);
    }
}
