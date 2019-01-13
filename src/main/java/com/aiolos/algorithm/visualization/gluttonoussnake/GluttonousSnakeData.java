package com.aiolos.algorithm.visualization.gluttonoussnake;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author aiolos
 * 2019-01-11
 */
public class GluttonousSnakeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public static final int[] UP = {-1, 0};
    public static final int[] RIGHT = {0, 1};
    public static final int[] DOWN = {1, 0};
    public static final int[] LEFT = {0, -1};
    public static final Color[] colors = {AlgoVisHelper.Blue, AlgoVisHelper.LightGreen,
            AlgoVisHelper.Amber, AlgoVisHelper.Purple, AlgoVisHelper.Black};

    public static final int[][] direction = {UP, RIGHT, DOWN, LEFT};
    private int N, M;
    public char[][] scene;
    public boolean[][] isBody;
    public Color[][] color;
    public List<Food> foods = new CopyOnWriteArrayList<>();
    public List<List<Snake>> snakes = new CopyOnWriteArrayList<>();

    public GluttonousSnakeData(int N, int M) {

        this.N = N;
        this.M = M;

        scene = new char[N][M];
        isBody = new boolean[N][M];
        color = new Color[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                scene[i][j] = ROAD;
            }
        }
        generateSnake();
        generateSnake();
        generateSnake();
        generateFood();
        generateFood();
        generateFood();
        generateFood();
        generateFood();
        generateFood();
        generateFood();
        generateFood();
    }

    public void move(List<Snake> snake, int[] d, Food food) {

        Snake head = snake.get(0);
        int newX = head.getX() + d[0];
        int newY = head.getY() + d[1];

        if (inFoodArea(newX, newY)) {
            snake.add(0, new Snake(newX, newY, d, head.getColor()));
            isBody[newX][newY] = true;
            color[newX][newY] = head.getColor();
            foods.remove(food);
            if (foods.size() < 9)
                generateFood();

            return;
        }

        Snake tail = snake.remove(snake.size() - 1);
        isBody[tail.getX()][tail.getY()] = false;
        color[tail.getX()][tail.getY()] = AlgoVisHelper.White;
        snake.add(0, new Snake(newX, newY, d, tail.getColor()));
        isBody[newX][newY] = true;
        color[newX][newY] = head.getColor();
    }

    public void generateSnake() {

        while (true) {
            int x = (int) (Math.random() * N);
            int y = (int) (Math.random() * M);
            if (inArea(x, y) && !isBody[x][y] && scene[x][y] != WALL ) {

                isBody[x][y] = true;
                int rand = (int) (Math.random() * colors.length);
                List<Snake> snake = new CopyOnWriteArrayList<>();
                snake.add(0, new Snake(x, y, colors[rand]));
                snakes.add(snake);
                break;
            }
        }
    }

    public void generateFood() {

        OUT:
        while (true) {
            int x = (int) (Math.random() * N);
            int y = (int) (Math.random() * M);
            if (inArea(x, y) && !isBody[x][y] && scene[x][y] != WALL) {
                Food food = new Food(x, y);
                foods.add(food);
                break OUT;
            }
        }
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean inFoodArea(int x, int y) {

        for (Food f: foods)
            if (f.isFood(x, y))
                return true;
        return false;
    }

    public Food getNearestFood(List<Snake> snake) {

        Snake head = snake.get(0);
        Food nearest = foods.get(0);
        double min = Double.MAX_VALUE;

        for (Food f : foods) {

            double d = Math.abs(head.getX() - f.getX()) * Math.abs(head.getY() - f.getY()) / 2;
            if (d < min) {
                min = d;
                nearest = f;
            }
        }
        return nearest;
    }
}
