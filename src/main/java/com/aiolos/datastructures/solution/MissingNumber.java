package com.aiolos.datastructures.solution;

/**
 * @author Aiolos
 * @date 2019-07-02 10:17
 */
public class MissingNumber {

    public static void main(String[] args) {

        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {

        // 0^0^9^1^6^2^4^3^2^4^3^5^5^6^7^7^0^8^1^9
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n = n^i;
            n = n ^ nums[i];
        }
        n = n^nums.length;
        return n;
    }
}
