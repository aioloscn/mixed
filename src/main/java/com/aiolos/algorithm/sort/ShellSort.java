package com.aiolos.algorithm.sort;

/**
 * @author Aiolos
 * @date 2021/7/10 9:35 上午
 */
public class ShellSort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int h = 1;
        // 计算increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h)
                    arr[j] = arr[j - h];

                arr[j] = e;
            }

            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 100000);
        SortHelper.testSort("com.aiolos.algorithm.sort.ShellSort", arr);
        SortHelper.printArray(arr);
    }
}
