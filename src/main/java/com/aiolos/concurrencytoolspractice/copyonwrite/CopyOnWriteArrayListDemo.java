package com.aiolos.concurrencytoolspractice.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示CopyOnWriteArrayList可以在迭代过程中修改数组内容，但是ArrayList不行
 * @author Aiolos
 * @date 2021/6/20 10:33 下午
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list: " + list);
            String next = iterator.next();
            System.out.println(next);

            if (next.equals("2")) {
                list.remove(3);
                list.remove(3);
            }
            if (next.equals("3"))
                list.add("3 found");
        }
    }
}
