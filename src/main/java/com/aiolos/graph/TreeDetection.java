package com.aiolos.graph;

/**
 * @author Aiolos
 * @date 2019-08-16 02:26
 */
public class TreeDetection {

    private Graph G;
    private int[] visited;
    private boolean hasCycle;
    private int cccount = 0;

    public TreeDetection(Graph G) {

        this.G = G;
        visited = new int[G.V()];

        for (int i = 0; i < visited.length; i ++)
            visited[i] = -1;

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                if (dfs(v, v, cccount ++)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int parent, int ccid) {

        if (ccid > 0)
            return false;

        visited[v] = ccid;
        for (int w : G.adj(v)) {
            if (visited[w] == -1) {
                if (dfs(w, v, ccid)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean isTree() {
        return cccount == 1 && !hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("tree.txt");
        TreeDetection treeDetection = new TreeDetection(g);
        System.out.println(treeDetection.isTree());
    }
}
