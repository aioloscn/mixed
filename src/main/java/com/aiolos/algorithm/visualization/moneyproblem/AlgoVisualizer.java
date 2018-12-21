package com.aiolos.algorithm.visualization.moneyproblem;

import com.aiolos.algorithm.visualization.circle.Circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private int[] money;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {

        money = new int[100];
        for (int i = 0; i < money.length; i ++)
            money[i] = 100;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        while (true) {

            Arrays.sort(money);
            // 绘制数据
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

            // 每进行绘制一次就更新k轮，加快游戏速度

            // 数据更新
            // 分钱50轮后重新绘制一次
            for (int k = 0; k < 50; k ++)
                for (int i = 0; i < money.length; i ++) {
//                    if (money[i] > 0) {
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
//                    }
                }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

    }

    private class AlgoMouseListener extends MouseAdapter {

    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 750;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
