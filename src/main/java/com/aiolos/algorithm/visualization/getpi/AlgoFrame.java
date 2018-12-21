package com.aiolos.algorithm.visualization.getpi;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setResizable(false);    // 先调用setResizable(false) 强制系统不能resize窗口再pack可解决windows中装饰的边距问题
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private Circle circle;
    private LinkedList<Point> points;

    public void render(Circle circle, LinkedList<Point> points) {
        this.circle = circle;
        this.points = points;
        // 将JFrame中的控件重新刷新一遍, 清空AlgoCanvas后重新调用paintCompenent()
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);    // 双缓存
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            AlgoVisHelper.setStrokeWidth(g2d, 3);
            AlgoVisHelper.setColor(g2d, Color.BLUE);
            AlgoVisHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());

            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                if (circle.contain(p))
                    AlgoVisHelper.setColor(g2d, Color.RED);
                else
                    AlgoVisHelper.setColor(g2d, Color.GREEN);
                AlgoVisHelper.fillCircle(g2d, p.x, p.y, 3);
            }
        }

        // 系统创建AlgoCanvas时自动调用这个方法，来决定画布的大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
