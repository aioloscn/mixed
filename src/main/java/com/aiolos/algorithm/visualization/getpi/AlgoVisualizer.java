package com.aiolos.algorithm.visualization.getpi;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

/**
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 10;

    private Circle circle;
    private int insideCircle = 0;
    private LinkedList<Point> points;
    private AlgoFrame frame;
    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        if (sceneWidth != sceneHeight)
            throw new IllegalArgumentException("This demo must be run in a square window");

        this.N = N;
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        points = new LinkedList<>();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Monte Carlo", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        for (int i = 0; i < N; i++) {

            if (i % 10 == 0) {
                frame.render(circle, points);
                AlgoVisHelper.pause(DELAY);

                if (points.size() != 0) {
                    int circleArea = insideCircle;
                    int squareArea = points.size();
                    double piEstimation = 4 * (double)insideCircle / squareArea;
                    System.out.println(piEstimation);
                }
            }

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
            if (circle.contain(p))
                insideCircle ++;
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

    }

    private class AlgoMouseListener extends MouseAdapter {

    }

    public static void main(String[] args) {

        int sceneWidth = 750;
        int sceneHeight = 750;
        int N = 10000;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
