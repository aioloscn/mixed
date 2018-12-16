package com.aiolos.datastructures.solution;

import java.util.*;

/**
 * @author aiolos
 * 2018-12-05
 */
public class Solution15 {

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int target = 0 - nums[i];
            int k = i + 1;
            int j = nums.length - 1;
            while (k < j) {
                if (nums[k] + nums[j] == target) {
                    List<Integer> arr = Arrays.asList(nums[i], nums[k], nums[j]);
                    ret.add(arr);
                    while (k < j && nums[k] == nums[k + 1])
                        k ++;
                    while (k < j && nums[j] == nums[j - 1])
                        j --;
                    k ++; j --;
                } else if (nums[k] + nums[j] < target)
                    k ++;
                else
                    j --;
            }
        }
        return ret;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
