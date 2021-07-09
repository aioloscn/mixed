package com.aiolos.algorithm.sort;

import java.util.Arrays;

/**
 * @author Aiolos
 * @date 2021/7/9 7:54 上午
 */
public class Main {
    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr1 = SortHelper.generateRandomArray(n, 100, 100000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        SortHelper.testSort("com.aiolos.algorithm.sort.SelectionSort", arr1);
        SortHelper.testSort("com.aiolos.algorithm.sort.InsertionSort", arr2);
    }
}
