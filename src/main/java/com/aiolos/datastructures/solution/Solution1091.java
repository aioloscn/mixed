package com.aiolos.datastructures.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aiolos
 * @date 2019-08-20 23:38
 */
public class Solution1091 {

    private static int R, C;
    private static int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static int shortestPathBinaryMatrix(int[][] grid) {

        R = grid.length;
        C = grid[0].length;
        if (grid[0][0] == 1) return -1;
        if (R == 1 && C == 1) return 1;

        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];
        Queue<Integer> queue = new LinkedList<>();
        visited[0][0] = true;
        dis[0][0] = 1;
        queue.add(0);

        while (!queue.isEmpty()) {

            int cur = queue.remove();
            int curx = cur / C;
            int cury = cur % C;

            for (int d = 0; d < 8; d ++) {

                int nextx = curx + dirs[d][0];
                int nexty = cury + dirs[d][1];

                if (inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 0) {

                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[curx][cury] + 1;
                    queue.add(nextx * C + nexty);

                    if (nextx == R - 1 && nexty == C - 1)
                        return dis[nextx][nexty];
                }
            }
        }
        return -1;
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {

        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
