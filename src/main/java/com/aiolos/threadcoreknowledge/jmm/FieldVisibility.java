package com.aiolos.threadcoreknowledge.jmm;

/**
 * 演示可见性问题
 * @author Aiolos
 * @date 2019-09-06 11:50
 */
public class FieldVisibility {

    int a = 1;
    // happens-before原则 只需要给b加volatile，b之前的所有操作都可见，所有b = a = 3，a一定=3
    volatile int b = 2;

    private void change() {
        a = 3;
        // b此时充当了触发器的作用，一定能看到它写入之前的所有操作
        b = a;
    }

    private void print() {
        System.out.println("b = " + b + ", a = " + a);
    }

    public static void main(String[] args) {

        for (;;) {
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
        }
    }


}
