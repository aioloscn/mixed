package com.aiolos.algorithm.sort;

import java.lang.reflect.Method;

/**
 * @author Aiolos
 * @date 2019-12-21 15:42
 */
@SuppressWarnings("unchecked")
public class SortHelper {

    private SortHelper() {}

    /**
     * 生成有n个元素的随机数组，每个元素的随机范围为[rangeL, rangeR]
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        // 需要VM options中添加-ea，不建议使用assert尤其不能保留到生产环境
        assert rangeL <= rangeR : "rangeL必须小于等于rangeR";
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    public static void printArray(Object[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i].compareTo(arr[i + 1])) > 0)
                return false;
        }
        return true;
    }

    public static void testSort(String sortClassName, Comparable[] arr) {

        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 获得sort方法
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});
            // 所要执行方法的参数
            Object[] params = new Object[]{arr};
            long startTime = System.currentTimeMillis();
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();
            assert isSorted(arr);
            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
