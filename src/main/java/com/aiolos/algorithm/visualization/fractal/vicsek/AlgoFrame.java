package com.aiolos.algorithm.visualization.fractal.vicsek;

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

            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);

//            drawFractal2(g2d, 0, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawFractal(Graphics2D g, int x, int y, int w, int h, int depth) {

            if (depth == data.depth) {
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.fillRectangle(g, x, y, w, h);
                return;
            }

            if (w <= 1 || h <= 1) {
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.fillRectangle(g, x, y, Math.max(w, 1), Math.max(h, 1));
                return;
            }

            int w_3 = w / 3;
            int h_3 = h / 3;
//            drawFractal(g, x, y, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 2, y, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3, y + h_3, w_3, h_3, depth + 1);
//            drawFractal(g, x, y + h_3 * 2, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 2, y + h_3 * 2, w_3, h_3, depth + 1);

            drawFractal(g, x + w_3, y, w_3, h_3, depth + 1);
            drawFractal(g, x, y + h_3, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3, y + h_3, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3 * 2, y + h_3, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3, y + h_3 * 2, w_3, h_3, depth + 1);
        }

        private void drawFractal2(Graphics2D g, int x, int y, int w, int h, int depth) {

            int w_3 = w / 3;
            int h_3 = h / 3;

            if (depth == data.depth) {
                AlgoVisHelper.setColor(g, Color.BLUE);
                AlgoVisHelper.fillRectangle(g, x + w_3, y + h_3, w_3, h_3);
                return;
            }

            if (w <= 1 || h <= 1)
                return;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        AlgoVisHelper.setColor(g, Color.BLUE);
                        AlgoVisHelper.fillRectangle(g, x + w_3, y + h_3, w_3, h_3);
                    } else
                        drawFractal2(g, x + i * w_3, y + j * h_3, w_3, h_3, depth + 1);
                }
            }
        }

        // 系统创建AlgoCanvas时自动调用这个方法，来决定画布的大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
