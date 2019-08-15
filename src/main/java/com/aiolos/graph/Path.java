package com.aiolos.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Aiolos
 * @date 2019-08-15 14:48
 */
public class Path {

    private Graph G;
    private int s;
    private int t;
    private int[] pre;

    public Path(Graph G, int s, int t) {

        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        pre = new int[G.V()];

        for (int i = 0; i < pre.length; i ++)
            pre[i] = -1;

        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {

        pre[v] = parent;
        if (v == t)
            return true;
        for (int w : G.adj(v))
            if (pre[w] == -1)
                if (dfs(w, v))
                    return true;
        return false;
    }

    /**
     * 源到目标节点是否连接
     * @param t target
     * @return
     */
    public boolean isConnectedTo(int t) {

        G.validateVertex(t);
        return pre[t] != -1;
    }

    public Iterable<Integer> path() {

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

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        Path path = new Path(g, 3, 0);
        System.out.println(path.path());
    }
}
