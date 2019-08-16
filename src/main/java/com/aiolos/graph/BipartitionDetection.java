package com.aiolos.graph;

import java.util.ArrayList;

/**
 * @author Aiolos
 * @date 2019-08-16 11:08
 */
public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

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

    public ArrayList<Integer>[] bipartitionGraph() {

        ArrayList<Integer>[] res = new ArrayList[2];
        res[0] = new ArrayList<>();
        res[1] = new ArrayList<>();
        for (int v = 0; v < colors.length; v ++) {
            if (colors[v] == 0)
                res[0].add(v);
            else
                res[1].add(v);
        }
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());

        ArrayList<Integer>[] res = bipartitionDetection.bipartitionGraph();
        for (int i = 0; i < res.length; i ++) {
            System.out.print(i + " : ");
            for (int v = 0; v < res[i].size(); v ++) {
                System.out.print(res[i].get(v) + " ");
            }
            System.out.println();
        }
    }
}
