package com.aiolos.algorithm.visualization.minesweeper;

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

    private MineSweeperData data;

    public void render(MineSweeperData data) {
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
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            int w = canvasWidth / data.M();
            int h = canvasHeight / data.N();

            for (int i = 0; i < data.N(); i++)
                for (int j = 0; j < data.M(); j++) {

                    if (data.open[i][j]) {
                        if (data.isMine(i, j))
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.mineImageURL);
                        else
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.numberImageURL(data.getNumber(i, j)));
                    } else {
                        if (data.flags[i][j])
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.flagImageURL);
                        else
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.blockImageURL);
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
