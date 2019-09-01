package com.aiolos.threadcoreknowledge.threadsafety;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 * @author Aiolos
 * @date 2019-09-01 16:58
 */
public class MultiThreadsPrivateError {

    private Map<String, String> states;
    public MultiThreadsPrivateError() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    /**
     * 返回一个副本，被修改不影响原来的states
     * @return
     */
    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {

        MultiThreadsPrivateError multiThreadsPrivateError = new MultiThreadsPrivateError();
        Map<String, String> states = multiThreadsPrivateError.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
