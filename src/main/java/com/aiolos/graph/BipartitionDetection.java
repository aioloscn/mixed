package com.aiolos.graph;

/**
 * @author Aiolos
 * @date 2019-08-16 11:08
 */
public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite;

    public BipartitionDetection(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];

        for (int i = 0; i < colors.length; i ++)
            colors[i] = -1;

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {

        visited[v] = true;
        colors[v] = color;

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {


    }
}
