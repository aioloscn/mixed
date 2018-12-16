package com.aiolos.datastructures.solution;

import java.util.*;

/**
 * @author aiolos
 * 2018-11-26
 */
public class Solution347 {

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        /*PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });*/
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
                (a,b) -> map.get(a) - map.get(b)
        );

        for (int key : map.keySet()) {
            if (pq.size() < k)
                pq.add(key);
            else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3};
        Solution347 s = new Solution347();
        System.out.println(s.topKFrequent(nums, 2));
    }
}
