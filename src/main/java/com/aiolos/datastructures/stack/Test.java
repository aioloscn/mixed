package com.aiolos.datastructures.stack;

import com.aiolos.datastructures.linkedlist.LinkedList;

/**
 * Created by aiolos on 2018-11-17.
 */
public class Test {
    public static void main(String[] args) {

//        ArrayStack stack = new ArrayStack();
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < 5; i ++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
