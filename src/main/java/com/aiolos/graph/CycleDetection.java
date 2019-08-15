package com.aiolos.graph;

import java.util.ArrayList;

/**
 * @author Aiolos
 * @date 2019-08-16 00:32
 */
public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {

        this.G = G;
        this.visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }

    }

    /**
     * 从顶点v开始，判断图中是否有环
     * @param v
     * @param parent
     * @return
     */
    private boolean dfs(int v, int parent) {

        visited[v] = true;
        for (int w : G.adj(v))
            if (!visited[w])
                if (dfs(w, v))
                    return true;
            else if (w != parent)
                return true;
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());
    }
}
