package com.aiolos.algorithm.visualization.fractal.koch;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 选择排序O(n*n) 是交换最少的排序
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 40;
    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int maxDepth, int side) {

        data = new FractalData(maxDepth);
        int sceneWidth = side;
        int sceneHeight = (int) (Math.ceil(Math.sqrt(Math.pow((double)side, 2.0) - Math.pow((double)side / 2, 2.0)) + (double)side / 3));

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
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

    private class TimerTaskTest extends TimerTask {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                setData(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Error in sleeping.");
                }
                if (i == 9)
                    i = 0;
            }
        }
    }

    private void executeTimerTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTaskTest(), 500);
    }

    public static void main(String[] args) {

        int maxDepth = 0;
        int side = 600;
        AlgoVisualizer visualizer = new AlgoVisualizer(maxDepth, side);

        visualizer.executeTimerTask();
    }
}
