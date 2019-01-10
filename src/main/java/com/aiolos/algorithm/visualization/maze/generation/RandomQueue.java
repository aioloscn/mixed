package com.aiolos.algorithm.visualization.maze.generation;

import java.util.LinkedList;

/**
 * @author aiolos
 * 2019-01-10
 */
public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<>();
    }

    public void add(E e) {
        if (Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove() {

        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Queue");

        if (Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
