package com.aiolos.algorithm.visualization.gluttonoussnake;

public class Food {

    private int x, y;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFood(int x, int y) {
        return this.x == x && this.y == y;
    }
}
