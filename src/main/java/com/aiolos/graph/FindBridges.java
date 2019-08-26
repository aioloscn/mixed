package com.aiolos.graph;

import java.util.ArrayList;

/**
 * @author Aiolos
 * @date 2019-08-26 16:09
 */
public class FindBridges {

    private class Edge{

        private int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return String.format("%d-%d", v, w);
        }
    }

    private Graph G;
    private boolean[] visited;
    private ArrayList<Edge> res;
    private int ord[];
    private int low[];
    private int cnt;

    public FindBridges(Graph G) {

        this.G = G;
        this.visited = new boolean[G.V()];
        this.res = new ArrayList<>();
        this.ord = new int[G.V()];
        this.low = new int[G.V()];
        this.cnt = 0;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt ++;

        for (int w : G.adj(v)) {

            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    // v-w是桥
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);  // low[w] = ord[v]
            }
        }
    }

    public ArrayList<Edge> getRes() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("bridges.txt");
        FindBridges findBridges = new FindBridges(g);
        System.out.println(findBridges.getRes());
    }
}
