package com.aiolos.datastructures.solution;

/**
 * @author Aiolos
 * @date 2019-05-10 15:54
 */
public class Solution541 {

    public String reverseStr(String s, int k) {

        char[] c = s.toCharArray();
        int len = s.length();

        if (len < k) {
            reverse(c, 0, len);
        } else {

            int count = 0;

            do {

                if (len - count * 2 * k < k) {
                    reverse(c, count * 2 * k, len);
                } else if (len - count * 2 * k <  2 * k || len - count * 2 * k >= k) {
                    reverse(c, count * 2 * k,  count * 2 * k + k);
                }

                count++;

            } while(len - count * 2 * k >= 2 * k);

            if (len - count * 2 * k < k) {
                reverse(c, count * 2 * k, len);
            } else if (count * 2 * k + k <= len && (len - count * 2 * k < 2 * k || len - count * 2 * k >= k)) {
                reverse(c, count * 2 * k, count * 2 * k + k);
            }
        }

        return String.valueOf(c);
    }

    private void reverse(char[] c, int i, int j) {

        j--;
        while (i < j) {
            swap(c, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] c, int i, int j) {

        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void main(String[] args) {

        String str = "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl";
        int k = 39;
        Solution541 solution = new Solution541();
        System.out.println(solution.reverseStr(str, k));
    }
}
