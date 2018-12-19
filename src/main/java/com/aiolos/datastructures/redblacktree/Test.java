package com.aiolos.datastructures.redblacktree;

import com.aiolos.datastructures.avltree.AVLTree;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        int n = 20000000;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i ++)
            list.add(i);
        long startTime = System.nanoTime();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x: list)
            rbt.add(x, null);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + "s");

        startTime = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x: list)
            avl.add(x, null);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.printf("AVLTree: " + time + "s\n");
        System.out.println(avl.isBST());
        System.out.println(avl.isBalanced());
    }
}
