package com.aiolos.designpattern.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by aiolos on 2018-11-01.
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private static boolean flag = true;
    private LazySingleton() {
        if (flag) {
            flag = false;
        } else {
            throw new RuntimeException("The singleton prohibits reflection calls");
        }
    }
    // 这里锁加在静态方法上相当于锁的这个类的class文件 synchronized (LazySingleton.class) {}
    // 如果锁的不是静态方法相当于锁的是堆内存中生成的对象
    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class objectClass = LazySingleton.class;
        Constructor constructor = objectClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingleton o1 = LazySingleton.getInstance();
        Field field = o1.getClass().getDeclaredField("flag");
        field.setAccessible(true);
        field.set(o1, true);
        LazySingleton o2 = (LazySingleton) constructor.newInstance();
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o1 == o2);
    }
}
