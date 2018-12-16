package com.aiolos.datastructures.unionfind;

/**
 * Created by aiolos on 2018-12-03.
 */
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size) {

        id = new int[size];
        for (int i = 0; i < size; i ++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找集合p所对应的编号集合
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[p];
    }

    /**
     * 查看元素p和元素q是否所属同一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;

        for (int i = 0; i < id.length; i ++)
            if (id[i] == pID)
                id[i] = qID;
    }
}
