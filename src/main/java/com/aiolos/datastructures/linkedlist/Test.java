package com.aiolos.datastructures.linkedlist;

/**
 * Created by aiolos on 2018-11-18.
 */
public class Test {
    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 5; i ++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 346);
        System.out.println(linkedList );

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeElements(4);
        System.out.println(linkedList);
    }
}
