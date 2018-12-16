package com.aiolos.datastructures.segmenttree;

/**
 * Created by aiolos on 2018-11-27.
 */
public class Test {
    public static void main(String[] args) {

               /* -2
                1   -3
             -2  3 -5  2
           -2  0 */
        Integer[] nums = {-2, 0, 3, -5, 2};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
        System.out.println(segTree);

//        System.out.println(segTree.query(2, 4));
        segTree.set(1, 6);
        System.out.println(segTree);
    }
}
