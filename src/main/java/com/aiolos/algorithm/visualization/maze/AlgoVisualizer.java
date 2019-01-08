package com.aiolos.algorithm.visualization.maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * 选择排序O(n*n) 是交换最少的排序
 * @author aiolos
 * 2018-12-21
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 7;
    private MazeData data;
    private AlgoFrame frame;

    private static final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String filename) {

        data = new MazeData(filename);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {

            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();;
        });
    }

    // 动画逻辑
    private void run() {

        setData(-1, -1, false);
//        if (!go(data.getEntranceX(), data.getEntranceY()))
//            System.out.println("The maze has no solution!");

        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (!stack.isEmpty()) {

            Position curPos = stack.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                isSolved = true;
                findPath(curPos);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        if (!isSolved)
            System.out.println("The maze has no solution!");

        setData(-1, -1, false);
    }

    private void findPath(Position des) {

        Position cur = des;
        while (cur != null) {

            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    // 从(x, y)的位置开始求解迷宫，如果求解成功，返回true，否则返回false
    private boolean go(int x, int y) {

        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x,y are out of index in go function");

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < d.length; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY])
                if (go(newX, newY))
                    return true;
        }

        setData(x, y, false);
        return false;
    }

    private void setData(int x, int y, boolean isPath) {

        if (data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {}

    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args) {

        String mazeFile = "resources/maze_101_101.txt";
        AlgoVisualizer visualizer = new AlgoVisualizer(mazeFile);
    }
}
