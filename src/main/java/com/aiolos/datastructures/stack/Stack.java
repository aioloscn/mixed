package com.aiolos.datastructures.stack;

/**
 * Created by aiolos on 2018-11-17.
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peak();
}
