package com.aiolos.algorithm.visualization.quicksort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 选择排序O(n*n) 是交换最少的排序，只需要交换n次，如果交换这个操作非常耗时的话，选择排序就成了最优的选择
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 20;
    private QuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new QuickSortData(N, sceneHeight);

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

        setData(-1, -1, -1, -1, -1);

        quickSort(0, data.N() - 1);

        setData(-1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r) {
        if (l > r)
            return;
        if (l == r) {
            setData(l, r, l, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1);
        int j = partition(l, r);
        quickSort(l, j - 1);
        quickSort(j + 1, r);
    }

    private int partition(int l, int r) {
        int p = (int) Math.random() * (r - l + 1) + l;
        setData(l, r, -1, p, -1);
        data.swap(p, l);
        int v = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            setData(l, r, -1, l, i);
            if (data.get(i) < v) {
                data.swap(++j, i);
                setData(l, r, -1, l, i);
            }
        }
        data.swap(j, l);
        setData(l, r, j, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement) {

        data.l = l;
        data.r = r;
        if (fixedPivot != -1)
            data.fixedPivots[fixedPivot] = true;
        data.curPivot = curPivot;
        data.curElement = curElement;

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
