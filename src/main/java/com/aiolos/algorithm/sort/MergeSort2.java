package com.aiolos.algorithm.sort;

import java.util.Arrays;

/**
 * 自底向上的归并排序算法
 * @author Aiolos
 * @date 2021/7/10 6:23 下午
 */
public class MergeSort2 {

    public static void sort(Comparable[] arr) {

        // 自底向上先把每16个元素的片段用插入排序排好，再按32...64...128...个元素做归并排序
        for (int i = 0; i < arr.length; i+= 16) {
            InsertionSort.sort(arr, i, Math.min(i + 15, arr.length - 1));
        }

        Comparable[] aux = new Comparable[arr.length];
        for (int sz = 16; sz <= arr.length; sz += sz) {
            for (int i = 0; i + sz < arr.length; i += sz + sz) {
                // 对arr[i...i+sz-1]和arr[i+sz...i+2*sz-1]这2个有序的部分进行归并成一个有序的部分
                // 防止i + sz + sz - 1越界，所以自底向上的归并排序不一定是平均划分的，层数差距最多差一层，但是不需要使用递归减少了这部分性能开销
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge(arr, aux, i, i + sz - 1, Math.min(i + sz + sz - 1, arr.length - 1));
            }
        }
    }

    private static void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
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

    private static void merge(Comparable[] arr, Comparable[] aux, int l, int mid, int r) {
        // 在这里再复制数据，因为[0...15]的数据已经经过插入排序排好序了，由于aux和arr大小一样，所以不需要再处理偏移量了
        for (int i = l; i <= r; i++) {
            aux[i] = arr[i];
        }
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j];
                j++;
            } else if (j > r) {
                arr[k] = aux[i];
                i++;
            } else if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {

        int n = 100;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 10000);
        SortHelper.testSort("com.aiolos.algorithm.sort.MergeSort", arr);
        SortHelper.printArray(arr);
    }
}
