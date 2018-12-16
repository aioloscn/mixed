package com.aiolos.design.pattern.creational.singleton;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aiolos
 * 2018-11-12
 */
public class ContainerSingleton {

    private ContainerSingleton() {}

    private static Map<String, Object> singletonMap = new HashMap<String, Object>();

    public static void putInstance(String key, Object instance) {
        if (StringUtils.isNoneBlank(key) && instance != null) {
            if (!singletonMap.containsKey(key)) {
                synchronized (ContainerSingleton.class) {
                    if (!singletonMap.containsKey(key)) {
                        singletonMap.put(key, instance);
                    }
                }
            }
        }
    }

    public static Object getInstance(String key) {
        return singletonMap.get(key);
    }
}
