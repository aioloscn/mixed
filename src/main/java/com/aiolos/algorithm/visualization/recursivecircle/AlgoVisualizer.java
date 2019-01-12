package com.aiolos.algorithm.visualization.recursivecircle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 选择排序O(n*n) 是交换最少的排序
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private CircleData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {

        int R = Math.min(sceneWidth, sceneHeight) / 2 - 2;
        data = new CircleData(sceneWidth / 2, sceneHeight / 2, R, R / 2, 3);

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
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

        int sceneWidth = 700;
        int sceneHeight = 700;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
