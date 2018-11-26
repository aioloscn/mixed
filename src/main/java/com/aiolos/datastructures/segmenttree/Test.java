package com.aiolos.datastructures.segmenttree;

/**
 * Created by aiolos on 2018-11-27.
 */
public class Test {
    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
        System.out.println(segTree);
    }
}
