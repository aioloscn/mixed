package com.aiolos.datastructures.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aiolos
 * 2018-12-29
 */
public class Solution929 {

    public int numUniqueEmails(String[] emails) {

        Map<String, Object> ret = new HashMap<>();
        for (String str: emails) {

            StringBuilder sb = new StringBuilder().append(str.substring(0, str.indexOf("+")).replaceAll(".", ""))
                    .append(str.substring(str.indexOf("@"), str.length()));
            ret.put(sb.toString(), null);
        }
        return ret.size();
    }
}
