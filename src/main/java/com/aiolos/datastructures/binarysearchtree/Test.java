package com.aiolos.datastructures.binarysearchtree;

/**
 * Created by aiolos on 2018-11-19.
 */
public class Test {
    public static void main(String[] args) {

        BST<Integer> bst = new BST<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums)
            bst.add(num);
//        bst.preOrder();
//        bst.preOrderNR();

//        bst.inOrder();
//        bst.inOrderNR();

//        bst.postOrder();
//        bst.postOrderNR();
//
//        bst.levelOrder();
//
//        System.out.println(bst.minimum() + "#" + bst.maximum());
//
//        System.out.println(bst.removeMin());
//        System.out.println(bst);
//        System.out.println(bst);

        bst.remove(3);
        System.out.println(bst);
    }
}
