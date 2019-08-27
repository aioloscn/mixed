package com.aiolos.graph;

import java.util.HashSet;

/**
 * @author Aiolos
 * @date 2019-08-27 13:27
 */
public class FindCutPoints {

    private Graph G;
    private boolean[] visited;
    private int[] ord;
    private int[] low;
    private int cnt;
    private HashSet<Integer> res;

    public FindCutPoints(Graph G) {

        this.G = G;
        this.visited = new boolean[G.V()];
        this.ord = new int[G.V()];
        this.low = new int[G.V()];
        this.cnt = 0;
        this.res = new HashSet<>();

        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt ++;
        int child = 0;

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                if (v != parent && low[w] >= ord[v])
                    res.add(v);
                child ++;
                if (v == parent && child > 1)
                    res.add(v);
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public HashSet<Integer> getRes() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("bridges.txt");
        FindCutPoints findCutPoints = new FindCutPoints(g);
        System.out.println(findCutPoints.getRes());
    }
}
