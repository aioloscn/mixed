package com.aiolos.datastructures.array;

import com.aiolos.datastructures.stack.ArrayStack;

/**
 * @author aiolos
 * 2018-11-16
 */
public class Test {
    public static void main(String[] args) {

        Array arr = new Array(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(10, "s");
        System.out.println(arr);
        arr.removeAllElement(8);
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);
    }
}
