package com.aiolos.algorithm.visualization.circle;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int sceneWidth = 600;
        int sceneHeight = 600;

        int N = 10;
        Circle[] circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int)(Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int)(Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int)(Math.random() * 11) - 5;
            int vy = (int)(Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                while (true) {
                    // 绘制数据
                    frame.render(circles);
                    AlgoVisHelper.pause(20);

                    // 数据更新
                    for (Circle circle: circles)
                        circle.move(0, 0, sceneWidth, sceneHeight);
                }
            }).start();
        });
    }
}
