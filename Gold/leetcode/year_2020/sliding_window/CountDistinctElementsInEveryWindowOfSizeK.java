package com.leetcode.year_2020.sliding_window;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
 * https://www.interviewbit.com/problems/distinct-numbers-in-window/
 *
 * @author neeraj on 18/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountDistinctElementsInEveryWindowOfSizeK {

    public static void main(String[] args) {
        System.out.println(countDistinctElementsInEveryWindowOfSizeK(
                Arrays.asList(1, 2, 1, 3, 4, 2, 3), 4
        ));

        System.out.println(countDistinctElementsInEveryWindowOfSizeK(
                Arrays.asList(1, 2, 4, 4), 2
        ));

        System.out.println(countDistinctElementsInEveryWindowOfSizeK(
                Arrays.asList(1, 2, 1, 3, 4, 3), 3
        ));
        System.out.println(countDistinctElementsInEveryWindowOfSizeK(
                Arrays.asList(80, 18, 80, 80, 80, 80, 80, 80, 94, 18), 8
        ));
    }

    public static List<Integer> countDistinctElementsInEveryWindowOfSizeK(List<Integer> arr, int k) {
        List<Integer> result = new ArrayList<>();
        /**
         * Initially we know every window can have a max of K distinct elements which is the size of window itself.
         */
        int begin = 0, end = 0;
        Map<Integer, Integer> itemFreqMap = new HashMap<>();

        // First populate the first window.
        while (end < k) {
            itemFreqMap.put(arr.get(end), itemFreqMap.getOrDefault(arr.get(end), 0) + 1);
            end++;
        }
        result.add(itemFreqMap.size());

        // We have 1 window ready, so let's complete rest of window by sliding the window by 1 unit.
        while (end < arr.size()) {

            // Sliding the window.
            // Delete item
            deleteItem(arr.get(begin), itemFreqMap);

            // Add Item
            addItem(arr.get(end), itemFreqMap);
            result.add(itemFreqMap.size());

            begin++;
            end++;
        }

        return result;
    }

    private static void deleteItem(Integer item, Map<Integer, Integer> itemFreqMap) {
        if (itemFreqMap.containsKey(item)) {
            if (itemFreqMap.get(item) == 1) {
                itemFreqMap.remove(item);
            } else {
                itemFreqMap.put(item, itemFreqMap.get(item) - 1);
            }
        }
    }

    private static void addItem(Integer item, Map<Integer, Integer> itemFreqMap) {
        itemFreqMap.put(item, itemFreqMap.getOrDefault(item, 0) + 1);
    }
}
