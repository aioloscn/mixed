package com.aiolos.threadcoreknowledge.threadsafety;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中新建线程，子线程中还没有初始化完毕，导致空指针异常
 * @author Aiolos
 * @date 2019-09-01 21:18
 */
public class MultiThreadsConstructorError {

    private Map<String, String> states;
    public MultiThreadsConstructorError() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "周一");
                states.put("2", "周二");
                states.put("3", "周三");
                states.put("4", "周四");
            }
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {

        MultiThreadsConstructorError multiThreadsConstructorError = new MultiThreadsConstructorError();
        Map<String, String> states = multiThreadsConstructorError.getStates();
        System.out.println(states.get("1"));
    }
}
