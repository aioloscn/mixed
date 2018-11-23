package com.aiolos.datastructures.set;

import com.aiolos.datastructures.linkedlist.LinkedList;

/**
 * @author aiolos
 * 2018-11-22
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    @Override
    public void add(E e) {
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElements(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
