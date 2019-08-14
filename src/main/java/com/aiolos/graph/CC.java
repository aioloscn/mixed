package com.aiolos.graph;

/**
 * connected component
 * @author Aiolos
 * @date 2019-08-14 17:45
 */
public class CC {

    private Graph G;
    private boolean[] visited;
    private int cccount;

    public CC(Graph G) {

        this.G = G;
        this.visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                dfs(v);
                cccount ++;
            }
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);
    }

    private int count() {
        return cccount;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }
}
