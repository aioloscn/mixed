package com.aiolos.datastructures.solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Aiolos
 * @date 2019-08-21 17:14
 */
public class Solution827 {

    private static boolean[][] visited;
    private static int R, C;
    private static HashMap<Integer, Integer> islandIndex = new HashMap<>();

    public static int largestIsland(int[][] grid) {

        R = grid.length;
        C = grid[0].length;
        if (C == 0) return 0;
        grid = grid;
        visited = new boolean[R][C];

        HashMap<Integer, Integer> islandArea = new HashMap<>();
        int index = 0;

        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j ++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    islandArea.put(index, dfs(i, j, index, grid));
                    index ++;
                }
            }
        }

        int res = 0;
        Set<Map.Entry<Integer, Integer>> entryset = islandArea.entrySet();
        for (Map.Entry<Integer, Integer> item : entryset) {
            res = Math.max(res, item.getValue());
        }

        for (int x = 0; x < R; x ++) {
            for (int y = 0; y < C; y ++) {
                if (!visited[x][y] && grid[x][y] == 0) {

                    int area = 0;
                    HashSet<Integer> indexset = new HashSet<>();
                    if (x - 1 >= 0 && islandIndex.containsKey((x - 1) * C + y)) {
                        indexset.add(islandIndex.get((x - 1) * C + y));
                    }
                    if (y + 1 < C && islandIndex.containsKey(x * C + (y + 1))) {
                        indexset.add(islandIndex.get(x * C + (y + 1)));
                    }
                    if (x + 1 < R && islandIndex.containsKey((x + 1) * C + y)) {
                        indexset.add(islandIndex.get((x + 1) * C + y));
                    }
                    if (y - 1 >= 0 && islandIndex.containsKey(x * C + (y - 1))) {
                        indexset.add(islandIndex.get(x * C + (y - 1)));
                    }
                    for (int idx : indexset) {
                        area += islandArea.get(idx);
                    }
                    res = Math.max(res, area + 1);
                }
            }
        }

        return res;
    }

    private static int dfs(int x, int y, int index, int[][] grid) {

        if (x < 0 || x >= R || y < 0 || y >= C || visited[x][y] || grid[x][y] == 0)
            return 0;

        visited[x][y] = true;
        islandIndex.put(x * C + y, index);
        int res = 1;

        res += dfs(x - 1, y, index, grid);
        res += dfs(x, y + 1, index, grid);
        res += dfs(x + 1, y, index, grid);
        res += dfs(x, y - 1, index, grid);
        return res;
    }

    public static void main(String[] args) {

        int[][] grid = {{1, 1}, {1, 1}};
        System.out.println(largestIsland(grid));
    }
}
