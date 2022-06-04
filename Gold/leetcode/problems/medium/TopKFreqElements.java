package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 06/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TopKFreqElements {

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println(topKFrequent(new int[]{1}, 1));
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys, (a, b) -> freq.get(b) - freq.get(a));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(keys.get(i));
        }
        return result;
    }
}
