package com.leetcode.year_2020;

import java.util.HashMap;

/**
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition(new int[]{1, 9, 8, 10}));
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));
        System.out.println(canPartition(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i;
        }

        if (total % 2 != 0) return false; // We can never partition this total.

        return canPartition(nums, 0, 0, total / 2, new HashMap<String, Boolean>());
    }

    public static boolean canPartition(int[] nums, int currentIndex,
                                       int sumTillNow, int sumToAchieve, HashMap<String, Boolean> memory) {

        String currentItem = currentIndex + "-" + sumTillNow;
        if (memory.containsKey(currentItem)) return memory.get(currentItem);

        // base Case
        if (sumTillNow == sumToAchieve) {
            return true;
        }
        if (currentIndex >= nums.length || sumTillNow > sumToAchieve) {
            return false;
        }

        // Now whether to choose the item or not choose
        boolean foundPartition = canPartition(nums, currentIndex + 1, sumTillNow, sumToAchieve, memory)
                || canPartition(nums, currentIndex + 1, sumTillNow + nums[currentIndex], sumToAchieve, memory);
        ;

        memory.put(currentItem, foundPartition);
        return foundPartition;

    }

}
