package com.aiolos.datastructures.solution;

/**
 * @author Aiolos
 * @date 2019-07-04 14:26
 */
public class MergeTwoArrays {

    public static void main(String[] args) {

        int[] nums1 = {2, 3, 5, 0, 0};
        int[] nums2 = {1, 6};
        merge(nums1, 3, nums2, 2);
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {

        int p = m + n - 1;
        int p1 = m - 1, p2 = n - 1;

        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = nums1[p1] < nums2[p2] ? nums2[p2--] : nums1[p1--];
        }

        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);

        for (int i : nums1) {
            System.out.println(i);
        }
    }
}
