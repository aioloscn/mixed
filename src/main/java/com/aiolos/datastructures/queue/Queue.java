package com.aiolos.datastructures.queue;

/**
 * Created by aiolos on 2018-11-17.
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
