package com.aiolos.graph;

/**
 * connected component
 * @author Aiolos
 * @date 2019-08-14 17:45
 */
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph G) {

        this.G = G;
        this.visited = new int[G.V()];

        for (int i = 0; i < visited.length; i ++)
            visited[i] = -1;

        for (int v = 0; v < G.V(); v ++) {
            if (visited[v] == -1) {
                dfs(v, cccount ++);
            }
        }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }

    private int count() {

        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return cccount;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }
}
