package com.aiolos.algorithm.visualization.circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int)(Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int)(Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int)(Math.random() * 11) - 5;
            int vy = (int)(Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

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

        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            // 数据更新
            if (isAnimated)
                for (Circle circle: circles)
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyChar() == ' ')
                isAnimated = !isAnimated;
        }
    }

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {

            // 点击frame包含了菜单栏的高度而不是panel真实的坐标，需要偏移这么一个高度
            event.translatePoint(0, frame.getBounds().height - frame.getCanvasHeight());
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 600;
        int sceneHeight = 600;
        int N = 10;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
