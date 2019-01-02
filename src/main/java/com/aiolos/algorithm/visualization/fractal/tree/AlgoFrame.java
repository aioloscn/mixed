package com.aiolos.algorithm.visualization.fractal.tree;

import javax.swing.*;
import java.awt.*;

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

    private FractalData data;

    public void render(FractalData data) {
        this.data = data;
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

            drawFractal(g2d, canvasWidth / 2, canvasHeight, canvasHeight, 0, 0);
        }

        private void drawFractal(Graphics2D g, double x1, double y1, double side, double angle, int depth) {

            double side_2 = side / 4;

            if (side <= 0)
                return;

            if (depth == data.depth) {
                double x2 = x1 - side * Math.sin(angle * Math.PI / 180.0);
                double y2 = y1 - side * Math.cos(angle * Math.PI / 180.0);
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }

            double x2 = x1 - side_2 * Math.sin(angle * Math.PI / 180.0);
            double y2 = y1 - side_2 * Math.cos(angle * Math.PI / 180.0);
            AlgoVisHelper.setColor(g, Color.BLUE);
            AlgoVisHelper.drawLine(g, x1, y1, x2, y2);

            drawFractal(g, x2, y2, side - side_2, angle + data.splitAngle / 2.5, depth + 1);
            drawFractal(g, x2, y2, side - side_2, angle - data.splitAngle / 1.5, depth + 1);
        }

        // 系统创建AlgoCanvas时自动调用这个方法，来决定画布的大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
