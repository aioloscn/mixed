package com.aiolos.datastructures.solution;

import java.util.*;

/**
 * @author Aiolos
 * @date 2019-08-22 13:19
 */
public class Solution773SlidingPuzzle {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private HashMap<String, String> pre = new HashMap<>();
    private int result;

    public int slidingPuzzle(int[][] board) {

        String str = arrayToString(board);
        if (str.equals("123450")) {
            result = 0;
            return result;
        }

        HashMap<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        visited.put(str, 0);
        queue.add(str);

        while (!queue.isEmpty()) {

            String cur = queue.remove();
            ArrayList<String> nextlist = getNext(stringToArray(cur));

            for (String next : nextlist) {

                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    pre.put(next, cur);
                    if (next.equals("123450")) {
                        result = visited.get(next);
                        return result;
                    }
                }
            }
        }

        result = -1;
        return result;
    }

    private ArrayList<String> path(int[][] board, String target) {

        ArrayList<String> path = new ArrayList<>();
        String cur = arrayToString(board);
        if (result > 0) {

            while (!cur.equals(target)) {
                path.add(target);
                target = pre.get(target);
            }
        }
        path.add(cur);
        Collections.reverse(path);
        return path;
    }

    private ArrayList<String> getNext(int[][] board) {

        ArrayList<String> res = new ArrayList<>();
        int zero;
        for (zero = 0; zero < 6; zero ++) {
            if (board[zero / 3][zero % 3] == 0) break;
        }

        int zx = zero / 3;
        int zy = zero % 3;

        for (int d = 0; d < 4; d ++) {

            int nextzx = zx + dirs[d][0];
            int nextzy = zy + dirs[d][1];
            if (nextzx >= 0 && nextzx < 2 && nextzy >= 0 && nextzy < 3) {

                swap(board, zx, zy, nextzx, nextzy);
                res.add(arrayToString(board));
                swap(board, zx, zy, nextzx, nextzy);
            }
        }
        return res;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {

        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private String arrayToString(int[][] board) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i ++) {
            sb.append(board[i / 3][i % 3]);
        }
        return sb.toString();
    }

    private int[][] stringToArray(String str) {

        char[] chars = str.toCharArray();
        int[][] res = new int[2][3];

        for (int i = 0; i < chars.length; i ++) {
            res[i / 3][i % 3] = chars[i] - '0';
        }
        return res;
    }

    public static void main(String[] args) {

        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        Solution773SlidingPuzzle solution = new Solution773SlidingPuzzle();
        System.out.println(solution.slidingPuzzle(board));
        System.out.println(solution.path(board, "123450"));
    }
}
