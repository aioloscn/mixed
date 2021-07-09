package com.aiolos.algorithm.visualization.insertionsort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 插入排序，在近乎有序的情况下时间复杂度可以降到O(n)
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 50;
    private InsertionSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, InsertionSortData.Type dataType) {

        data = new InsertionSortData(N, sceneHeight, dataType);

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

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }

    // 动画逻辑
    private void run() {

        setData(0, -1);

        for (int i = 1; i < data.N(); i++) {

            setData(i, i);
            // 第一版
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i + 1, j - 1);
            }

            // 第二版，算法效率更高，可视化效果偏差
            /*int copy = data.get(i);
            int j = i;
            for (; j > 0 && copy < data.get(j - 1); j--) {
                data.set(j, data.get(j - 1));
                setData(i, j - 1);
            }
            data.set(j, copy);*/
        }

        setData(data.N(), -1);
    }

    private void setData(int orderedIndex, int currentIndex) {

        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

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
