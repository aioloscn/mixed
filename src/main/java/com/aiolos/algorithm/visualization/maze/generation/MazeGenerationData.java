package com.aiolos.algorithm.visualization.maze.generation;

public class MazeGenerationData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';


    private int N, M;
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] inMist;
    public boolean[][] path;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeGenerationData(int N, int M) {

        if (N % 2 == 0 || M % 2 == 0)
            throw new IllegalArgumentException("Our Maze Generation Algorithm requires the width and height of the maze are odd numbers");

        this.N = N;
        this.M = M;
        maze = new char[N][M];
        visited = new boolean[N][M];
        inMist = new boolean[N][M];
        path = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
                inMist[i][j] = true;
            }

        if (Math.random() < 0.5) {
            int randX = (int) (Math.random() * (N - 2));
            entranceX = randX % 2 == 0 ? randX + 1 : randX;
            entranceY = Math.random() < 0.5 ? 0 : M - 1;
        } else {
            entranceX = Math.random() < 0.5 ? 0 : N - 1;
            int randY = (int) (Math.random() * (M - 2));
            entranceY = randY % 2 == 0 ? randY + 1 : randY;
        }
        if (Math.random() < 0.5) {
            int randX = (int) (Math.random() * (N - 2));
            exitX = randX % 2 == 0 ? randX + 1 : randX;
            exitY = Math.random() < 0.5 ? 0 : M - 1;
        } else {
            exitX = Math.random() < 0.5 ? 0 : N - 1;
            int randY = (int) (Math.random() * (M - 2));
            exitY = randY % 2 == 0 ? randY + 1 : randY;
        }
        System.out.println(entranceX + "#" + entranceY + "#" + exitX + "#" + exitY);

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void openMist(int x, int y) {

        if (!inArea(x, y))
            throw new IllegalArgumentException("x or y is out of index in openMist");

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++)
                if (inArea(i, j))
                    inMist[i][j] = false;
        }
        return;
    }
}
