package com.aiolos.datastructures.solution;

/**
 * @author Aiolos
 * @date 2019-04-27 16:53
 */
public class BinarySearchSolution {

    public static int binarySearch(int[] source, int target) {

        int len = source.length;
        int l = 0;
        int r = len - 1;
        int mid = l + (r - l) / 2;

        if (target > source[r] || target < source[0])
            return -1;

        if (source[mid] == target)
            return mid;

        while (l <= r) {

            mid = l + (r - l) / 2;
            if (source[mid] >= target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }

    public static int nextGreatest(int[] source, int target) {

        int len = source.length;
        int l = 0;
        int r = len - 1;
        int mid = l + (r - l) / 2;

        if (source[0] > target || source[r] <= target)
            return source[0];

        while (l < r) {

            mid = l + (r - l) / 2;
            if (source[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }

        return source[l];
    }

    public static void main(String[] args) {

        int[] source = {1, 3, 5, 6};
        int target = 1;
        System.out.println(nextGreatest(source, target));
    }
}
