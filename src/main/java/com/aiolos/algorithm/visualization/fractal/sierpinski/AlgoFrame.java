package com.aiolos.algorithm.visualization.fractal.sierpinski;

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

            drawFractal(g2d, 0, canvasHeight, canvasWidth, 0);
        }

        private void drawFractal(Graphics2D g, int Ax, int Ay, int side, int depth) {

            if (side <= 1) {
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.fillRectangle(g, Ax, Ay, 1, 1);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;

            int h = (int) (Math.sin(60.0 * Math.PI / 180.0) * side);
            int Cx = Ax + side / 2;
            int Cy = Ay - h;

            if (depth == data.depth) {
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.fillTriangle(g, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int AB_centerx = (Ax + Bx) / 2;
            int AB_centery = (Ay + By) / 2;
            int AC_centerx = (Ax + Cx) / 2;
            int AC_centery = (Ay + Cy) / 2;

            drawFractal(g, Ax, Ay, side / 2, depth + 1);
            drawFractal(g, AB_centerx, AB_centery, side / 2, depth + 1);
            drawFractal(g, AC_centerx, AC_centery, side / 2, depth + 1);
        }

        // 系统创建AlgoCanvas时自动调用这个方法，来决定画布的大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
