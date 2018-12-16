package com.aiolos.datastructures.map;

/**
 * Created by aiolos on 2018-11-23.
 */
public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
