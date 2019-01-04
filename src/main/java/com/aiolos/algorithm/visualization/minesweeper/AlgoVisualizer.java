package com.aiolos.algorithm.visualization.minesweeper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

/**
 * 选择排序O(n*n) 是交换最少的排序
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private static int blockSide = 32;
    private MineSweeperData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M, int mineNumber) {

        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();;
        });
    }

    // 动画逻辑
    private void run() {

        setData();
    }

    private void setData() {

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {}

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 20;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M, mineNumber);
    }
}
