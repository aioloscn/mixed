package com.aiolos.threadcoreknowledge.threadsafety;

/**
 * 未初始化完毕，就this赋值
 * @author Aiolos
 * @date 2019-09-01 20:24
 */
public class MultiThreadsThisError {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(10);
        // 因为时间的不同而造成Point数据不同，线程不安全
//        Thread.sleep(105);
        if (point != null) {
            System.out.println(point);
        }
    }
}

class Point {
    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadsThisError.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}

class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}