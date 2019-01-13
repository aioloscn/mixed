package com.aiolos.algorithm.visualization.gluttonoussnake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.List;

/**
 * @author aiolos
 * 2019-01-11
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
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
                run(data.snakes.get(0));
            }).start();

            new Thread(() -> {
                run(data.snakes.get(1));
            }).start();

            new Thread(() -> {
                run(data.snakes.get(2));
            }).start();

        });
    }

    private void run(List<Snake> snake) {

        setData();
        while (true) {
            go(snake);
        }
    }

    private void go(List<Snake> snake) {

        Food food = data.getNearestFood(snake);
        findFood(snake, food);
    }

    private void findFood(List<Snake> snake, Food food) {

        Snake s = snake.get(0);

        while (!food.isFood(s.getX(), s.getY())) {

            if (s.getX() < food.getX()) {
                int[] d = GluttonousSnakeData.DOWN;
                if (s.getD() != null)
                    d = check(s, d);
                data.move(snake, d, food);
                s = snake.get(0);   // move中当前snake的位置已经移动，但s所引用的数据仍是原数据，需同步
                setData();
            } else if (s.getX() > food.getX()) {
                int[] d = GluttonousSnakeData.UP;
                if (s.getD() != null)
                    d = check(s, d);
                data.move(snake, d, food);
                s = snake.get(0);
                setData();
            }

            if (s.getY() < food.getY()) {
                int[] d = GluttonousSnakeData.RIGHT;
                if (s.getD() != null)
                    d = check(s, d);
                data.move(snake, d, food);
                s = snake.get(0);
                 setData();
            } else if (s.getY() > food.getY()) {
                int[] d = GluttonousSnakeData.LEFT;
                if (s.getD() != null)
                    d = check(s, d);
                data.move(snake, d, food);
                s = snake.get(0);
                setData();
            }
        }
    }

    private int[] check(Snake s, int[] d) {

        int newX = s.getX() + d[0];
        int newY = s.getY() + d[1];
        if (data.inArea(newX, newY) && data.isBody[newX][newY]) {

            for (int i = 0; i < GluttonousSnakeData.direction.length; i++) {

                int[] newDirection = GluttonousSnakeData.direction[i];
                int x = s.getX() + newDirection[0];
                int y = s.getY() + newDirection[1];
                if (data.inArea(x, y) && s.getD() != null && s.getD() != newDirection && !data.isBody[x][y]) {

                    return newDirection;
                }
            }
        }
        return d;
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
