package com.aiolos.algorithm.visualization.gluttonoussnake;

import java.util.LinkedList;

/**
 * @author aiolos
 * 2019-01-11
 */
public class GluttonousSnakeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public static final char BODY = '*';
    public static final char FOOD = '$';
    public static final int[] UP = {-1, 0};
    public static final int[] RIGHT = {0, 1};
    public static final int[] DOWN = {1, 0};
    public static final int[] LEFT = {0, -1};

    private int N, M;
    public int speed = 10;
    public char[][] scene;
    public LinkedList<int[]> queue = new LinkedList<>();

    public GluttonousSnakeData(int N, int M) {

        this.N = N;
        this.M = M;

        scene = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                scene[i][j] = ROAD;
            }
        }
        scene[1][3] = BODY;
        scene[1][2] = BODY;
        scene[1][1] = BODY;
        queue.add(new int[]{1, 3});
        queue.add(new int[]{1, 2});
        queue.add(new int[]{1, 1});
    }

    public void move(int[] a, int[] b) {
        scene[a[0]][a[1]] = ROAD;
        int[] l = queue.peek();
        int newX = l[0] + b[0];
        int newY = l[1] + b[1];
        scene[newX][newY] = BODY;
        queue.addFirst(new int[]{newX, newY});
    }

    public void generateFood() {

        while (true) {
            int x = (int) (Math.random() * N);
            int y = (int) (Math.random() * M);
            if (!queue.contains(new int[]{x, y})) {
                scene[x][y] = FOOD;
                break;
            }
        }
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }
}
