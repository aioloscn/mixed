package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2021/7/9 3:31 下午
 */
public class BubbleSort {

    public static void sort(Comparable[] arr) {
        // 第一版
        /*for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) < 0)
                    swap(arr, j, j + 1);
            }
        }*/

        // 第二版
        int n = arr.length;
//        boolean swapped;
        int newn;
        do {
//            swapped = false;
            newn = 0;
            // 每一轮排序将最大值放在最后，下一次排序就可以跳过了
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i, i - 1);
//                    swapped =  true;
                    newn = i;
                }
            }
            n--;
        } while (newn > 0);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 100000);
        SortHelper.testSort("com.aiolos.algorithm.sort.BubbleSort", arr);
        SortHelper.printArray(arr);
    }
}
