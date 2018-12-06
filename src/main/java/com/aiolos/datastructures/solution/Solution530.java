package com.aiolos.datastructures.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aiolos
 * 2018-12-05
 */
public class Solution530 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int getMinimumDifference(TreeNode root) {
        List<Integer> arr = new ArrayList();
        inorder(root, arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size() - 1; i ++) {
            min = Math.min(min, Math.abs(arr.get(i) - arr.get(i + 1)));
        }
        return min;
    }

    private void inorder(TreeNode node, List<Integer> arr) {

        if (node == null)
            return;
        inorder(node.left, arr);
        arr.add(node.val);
        inorder(node.right, arr);
    }
}
