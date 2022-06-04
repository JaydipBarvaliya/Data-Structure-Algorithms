package com.leetcode.year_2020.prefix_sum_technique;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
 *
 * @author neeraj on 20/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LargestSubArrayWithZeroSum {

    public static void main(String[] args) {
        System.out.println(findLargestSubArrayWithZeroSum(new int[]{
                15, -2, 2, -8, 1, 7, 10, 23
        }));
        System.out.println(findLargestSubArrayWithZeroSum(new int[]{
                1, 2, 3
        }));
        System.out.println(findLargestSubArrayWithZeroSum(new int[]{
                1, 0, 3
        }));
    }

    public static int findLargestSubArrayWithZeroSum(int[] nums) {
        /**
         * This is again a prefix sum technique. Once we encounter
         * a same prefix sum we know something must have happened between those indexes which
         * can lead to a zero sum.
         *
         * Also our prefix_sum_at_y = prefix_sum_at_x + target_sum theory from CTCI
         * and here the target sum is 0.
         *
         * Now since the question asks to find the largest SubArray so we will not update the position
         * in the Map and keep the one which we find initially.
         */
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumFirstOccurrence = new HashMap<>();
        int LargestSubArray = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            if (prefixSumFirstOccurrence.containsKey(prefixSum)) {
                LargestSubArray = Math.max(LargestSubArray, i - prefixSumFirstOccurrence.get(prefixSum));
            } else {
                prefixSumFirstOccurrence.put(prefixSum, i);
            }
        }
        return LargestSubArray;
    }
}
