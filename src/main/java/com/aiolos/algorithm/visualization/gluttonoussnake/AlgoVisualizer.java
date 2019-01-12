package com.aiolos.algorithm.visualization.gluttonoussnake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * @author aiolos
 * 2019-01-11
 */
public class AlgoVisualizer {

    private static int DELAY = 10;
    private static int blockSide = 7;
    private GluttonousSnakeData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M) {

        data = new GluttonousSnakeData(N, M);
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.M() * blockSide;

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Gluttonous Snake", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        go();
    }

    private void go() {

        for (int i = 0; i < 90; i++) {
            data.move(data.queue.removeLast(), data.RIGHT);
            setData();
        }
    }

    private void setData() {

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {}

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        int N = 101;
        int M = 101;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M);
    }
}
