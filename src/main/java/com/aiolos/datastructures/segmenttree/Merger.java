package com.aiolos.datastructures.segmenttree;

/**
 * Created by aiolos on 2018-11-27.
 */
public interface Merger<E> {

    E merge(E a, E b);
}
