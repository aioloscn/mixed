package com.aiolos.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Unweighted Single Source Shortest Path
 * @author Aiolos
 * @date 2019-08-18 15:31
 */
public class USSSPath {

    private Graph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    public USSSPath(Graph G, int s) {

        this.G = G;
        this.s = s;
        this.dis = new int[G.V()];
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];

        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
            dis[i] = -1;
        }

        bfs(s);
    }

    private void bfs(int s) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;

        while (!queue.isEmpty()) {

            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {

        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t))
            return res;

        int cur = t;
        while (cur != s) {

            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public int distance(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        USSSPath ss = new USSSPath(g, 0);
        System.out.println(ss.path(6));
        System.out.println(ss.distance(6));
    }
}
