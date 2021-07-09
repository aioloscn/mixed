package com.aiolos.algorithm.visualization.bubblesort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 选择排序O(n*n) 是交换最少的排序，只需要交换n次，如果交换这个操作非常耗时的话，选择排序就成了最优的选择
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 10;
    private BubbleSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new BubbleSortData(N, sceneHeight);

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

        setData(data.N(), -1);

        // 第一版
        /*for (int i = 0; i < data.N() - 1; i++) {
            for (int j = 0; j < data.N() - 1 - i; j++) {
                setData(data.N() - i, j);
                if (data.get(j) > data.get(j + 1)) {
                    data.swap(j, j + 1);
                }
            }
        }*/

        // 第二版
        int n = data.N();
        int newn;
        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                setData(n, i);
                if (data.get(i - 1) > data.get(i)) {
                    data.swap(i, i - 1);
                    newn = i;
                    setData(n, i);
                }
            }
            n--;
        } while (newn > 0);

        setData(0, -1);
    }

    private void setData(int orderedIndex, int currentCompareIndex) {

        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;

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
