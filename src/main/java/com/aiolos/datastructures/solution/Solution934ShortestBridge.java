package com.aiolos.datastructures.solution;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aiolos
 * @date 2019-08-28 16:30
 */
public class Solution934ShortestBridge {

    private int[][] A;
    private boolean[] visited;
    private int R, C;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int shortestBridge(int[][] A) {

        this.A = A;
        this.R = A.length;
        this.C = A[0].length;
        this.visited = new boolean[R * C];
        int res = Integer.MAX_VALUE;
        HashSet<HashSet<Integer>> arr = new HashSet<>();

        for (int v = 0; v < R * C; v ++) {
            if (!visited[v] && A[v / C][v % C] == 1) {
                HashSet<Integer> set = new HashSet<>();
                dfs(v, set);
                arr.add(set);
            }
        }

        visited = new boolean[R * C];

        for (HashSet<Integer> set : arr) {

                for (int v : set) {

                    if (visited[v]) continue;
                    int result = 0;
                    HashMap<Integer, Integer> vst = new HashMap<>();
                    Queue<Integer> queue = new LinkedList<>();
                    vst.put(v, 0);
                    queue.add(v);

                inner : {
                    while (!queue.isEmpty()) {

                        int w = queue.remove();
                        boolean flag = false;
                        for (int d = 0; d < 4; d ++) {

                            int nextx = w / C + dirs[d][0];
                            int nexty = w % C + dirs[d][1];
                            if (nextx >= 0 && nextx < R && nexty >= 0 && nexty < C && !vst.containsKey(nextx * C + nexty) && A[nextx][nexty] == 0) {
                                queue.add(nextx * C + nexty);
                                vst.put(nextx * C + nexty, vst.get(w) + 1);
                            } else if (nextx >= 0 && nextx < R && nexty >= 0 && nexty < C && !set.contains(nextx * C + nexty) && A[nextx][nexty] == 1) {
                                res = Math.min(res, vst.get(w));
                                visited[nextx * C + nexty] = true;
                                break inner;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    private void dfs(int v, HashSet<Integer> set) {

        visited[v] = true;
        set.add(v);
        for (int d = 0; d < 4; d ++) {

            int nextx = v / C + dirs[d][0];
            int nexty = v % C + dirs[d][1];
            if (nextx >= 0 && nextx < R && nexty >= 0 && nexty < C && !visited[nextx * C + nexty] && A[nextx][nexty] == 1) {
                dfs(nextx * C + nexty, set);
            }
        }
    }

    public static void main(String[] args) {

        long begin = System.nanoTime();
        Solution934ShortestBridge shortestBridge = new Solution934ShortestBridge();
        int[][] A = {{0,1,0,0,0,0},{0,1,1,1,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{1,1,0,0,0,0}};
        long end = System.nanoTime() - begin;
        System.out.println(shortestBridge.shortestBridge(A));
        System.out.println(new BigDecimal(String.valueOf(end)).divide(new BigDecimal("1000000")));
    }
}
