package com.aiolos.datastructures.solution;

/**
 * @author aiolos
 * 2018-12-28
 */
public class Solution771 {

    public static int numJeweInStones(String J, String S) {

        char[] j = J.toCharArray();
        char[] s = S.toCharArray();
        int count[] = new int['z' - 'A' + 1];
        for (char c: j)
            count[c - 'A']++;

        int sum = 0;
        for (char c: s)
            sum += count[c - 'A'];
        return sum;
    }

    public static void main(String[] args) {

        String J = "aA";
        String S = "aAAbbbb";
        String[] a = new String[10];
        System.out.println(numJeweInStones(J, S));
    }
}
