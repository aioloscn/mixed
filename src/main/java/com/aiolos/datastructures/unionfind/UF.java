package com.aiolos.datastructures.unionfind;

/**
 * Created by aiolos on 2018-12-03.
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
