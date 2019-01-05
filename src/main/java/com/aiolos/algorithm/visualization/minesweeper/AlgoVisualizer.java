package com.aiolos.algorithm.visualization.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();;
        });
    }

    // 动画逻辑
    private void run() {

        setData(false, -1, -1);
    }

    private void setData(boolean isLeftClicked, int x, int y) {

        if (data.inArea(x, y)) {
            if (isLeftClicked) {
                if (data.isMine(x, y)) {
                    // Game Over
                    data.open[x][y] = true;
                } else {
                    data.open(x, y);
                }
            } else
                data.flags[x][y] = !data.flags[x][y];
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {}

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event) {

            event.translatePoint(-(int) (frame.getBounds().width - frame.getCanvasWidth()) / 2, -(int) (frame.getBounds().height - frame.getCanvasHeight()));
            System.out.println(event.getPoint());
            Point pos = event.getPoint();
            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();
            int x = pos.y / h;  // 行号
            int y = pos.x / w;  // 列号
            setData(SwingUtilities.isLeftMouseButton(event), x, y);
        }
    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 20;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M, mineNumber);
    }
}
