package com.aiolos.algorithm.sort;

import java.util.Arrays;

/**
 * @author Aiolos
 * @date 2019-12-22 21:22
 */
public class MergeSort {

    private MergeSort() {}

    public static void sort(Comparable[] arr) {

        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {

        // 每一个部分需要排序的范围缩小到16个的时候使用插入排序相比于16个元素做归并排序会更加效率
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int mid = (r - l) / 2 + l;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        // 对于有序的情况不需要归并，if语句的判断本身也要消耗一定的性能，但总体影响不大
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

        // 因为复制的aux索引是从0开始的，所以计算时需要加上l的偏移量
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

    private static void sort(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int mid = (r - l) / 2 + l;
        sort(arr, aux, l, mid);
        sort(arr, aux, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, aux, l, mid, r);
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

        int n = 10000000;
        Integer[] arr = SortHelper.generateRandomArray(n, 100, 100000000);
        SortHelper.testSort("com.aiolos.algorithm.sort.MergeSort", arr);
//        SortHelper.printArray(arr);
    }
}
