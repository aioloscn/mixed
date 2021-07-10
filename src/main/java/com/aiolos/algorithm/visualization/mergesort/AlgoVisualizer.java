package com.aiolos.algorithm.visualization.mergesort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

/**
 * 归并排序 O(nlogn)
 * @author aiolos
 * 2021-07-10
 */
public class AlgoVisualizer {

    private static int DELAY = 60;
    private MergeSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new MergeSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        setData(-1, -1, -1);

        mergeSort(0, data.numbers.length - 1);

        setData(0, data.numbers.length - 1, data.numbers.length - 1);
    }

    private void mergeSort(int l, int r) {
        if (l >= r)
            return;

        setData(l, r, -1);
        int mid = (r - l) / 2 + l;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k ++) {
            if (i > mid) {
                data.numbers[k] = aux[j - l];
                j++;
            } else if (j > r) {
                data.numbers[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l];
                i++;
            } else {
                data.numbers[k] = aux[j - l];
                j++;
            }

            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex) {

        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {}

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        int sceneWidth = 700;
        int sceneHeight = 700;
        int N = 100;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
