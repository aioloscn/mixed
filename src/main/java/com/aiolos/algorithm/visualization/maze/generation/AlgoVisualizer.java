package com.aiolos.algorithm.visualization.maze.generation;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.Stack;

/**
 * 选择排序O(n*n) 是交换最少的排序
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 1;
    private static int blockSide = 7;
    private static final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeGenerationData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M) {

        data = new MazeGenerationData(N, M);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();;
        });
    }

    // 动画逻辑
    private void run() {

        setData(-1, -1);

//        go(data.getEntranceX(), data.getEntranceY() + 1);
//        stackGo();
        queueGo();

        setData(-1, -1);
    }

    /**
     * 深度优先递归生成迷宫
     * @param x
     * @param y
     */
    private void go(int x, int y) {

        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x,y are out of index in go function");

        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0] * 2;
            int newY = y + d[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + d[i][0], y + d[i][1]);
                go(newX, newY);
            }
        }
    }

    /**
     * 深度优先非递归生成迷宫
     */
    private void stackGo() {

        Stack<Position> stack = new Stack<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.push(first);
        data.visited[first.getX()][first.getY()] = true;

        while (!stack.isEmpty()) {
            Position curPos = stack.pop();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    /**
     * 广度优先生成迷宫
     */
    private void queueGo() {

        RandomQueue<Position> queue = new RandomQueue<>();
        int x = 0, y = 0;
        if (data.getEntranceX() == 0) {
            x = data.getEntranceX() + 1;
            y = data.getEntranceY();
        } else if (data.getEntranceX() == 100) {
            x = data.getEntranceX() - 1;
            y = data.getEntranceY();
        }
        if (data.getEntranceY() == 0) {
            x = data.getEntranceX();
            y = data.getEntranceY() + 1;
        } else if (data.getEntranceY() == 100) {
            x = data.getEntranceX();
            y = data.getEntranceY() - 1;
        }
        Position first = new Position(x, y);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(), first.getY());

        while (queue.size() != 0) {
            Position curPos = queue.remove();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    /**
     * 在随机生成的迷宫寻找解
     * @param x
     * @param y
     * @return
     */
    private boolean autoGo(int x, int y) {

        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x,y are out of index in go function");

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < d.length; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.maze[newX][newY] == MazeGenerationData.ROAD && !data.visited[newX][newY])
                if (autoGo(newX, newY))
                    return true;
        }

        setData(x, y, false);
        return false;
    }

    private void setData(int x, int y) {

        if (data.inArea(x, y))
            data.maze[x][y] = MazeGenerationData.ROAD;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(int x, int y, boolean isPath) {

        if (data.inArea(x, y))
            data.path[x][y] = isPath;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                for (int i = 0; i < data.N(); i++) {
                    for (int j = 0; j < data.M(); j++) {
                        data.visited[i][j] = false;
                        data.path[i][j] = false;
                    }
                }

                new Thread(() -> {
                    autoGo(data.getEntranceX(), data.getEntranceY());
                }).start();;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        int N = 101;
        int M = 101;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M);
    }
}
