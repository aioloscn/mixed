package com.aiolos.algorithm.visualization.fractal.tree;

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
    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int maxDepth, double splitAngle) {

        data = new FractalData(maxDepth, splitAngle);

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();;
        });
    }

    // 动画逻辑
    private void run() {

        setData(data.depth);
    }

    private void setData(int depth) {

        if (depth >= 0)
            data.depth = depth;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9') {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        int maxDepth = 6;
        double splitAngle = 50.0;
        int sceneWidth = 800;
        int sceneHeight = 800;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, maxDepth, splitAngle);
    }
}
