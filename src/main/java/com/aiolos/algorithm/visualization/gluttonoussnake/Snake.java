package com.aiolos.algorithm.visualization.gluttonoussnake;

import java.awt.*;

public class Snake {

    private int x, y;
    private int[] d;
    private Color color;

    public Snake(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Snake(int x, int y, int[] d, Color color) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getD() {
        return d;
    }

    public void setD(int[] d) {
        this.d = d;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBody(int x, int y) {
        return this.x == x && this.y == y;
    }
}
