package com.aiolos.algorithm.visualization.getpi;

import java.awt.*;

public class Circle {

    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    /**
     * 判断鼠标点击的坐标是否在圆内
     * 判断这个点到圆心的距离是否小于r
     * 三角形的斜边就是到圆心的距离，判断是否小于r
     * @param p
     * @return
     */
    public boolean contain(Point p) {
        // x - p.x和 p.x - x一样
        return Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) <= r * r;
    }
}
