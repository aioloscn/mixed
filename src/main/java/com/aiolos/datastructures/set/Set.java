package com.aiolos.datastructures.set;

/**
 * @author aiolos
 * 2018-11-22
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
