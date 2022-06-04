package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 15/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{28, 54, 7, -70, 22, 65, -6}, 100));
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        // We need to maintain the cumulative sum and we can use it to
        // calculate the subarray of sum k.
        // Example
        /**
         * nums :            [3, 4, 7, 2, -3, 1, 4, 2]
         * Cumulative Sum:[0, 3, 7, 14, 16, 13, 14, 18, 20]
         *
         * Now if i take the Difference of any 2 cumulative sum
         * and it's `k` we will get the Subarray.
         *
         * 7-0 = 7 So Subarray = [3, 4]
         * 14 - 7 = 7 So SubArray = [7]
         * 14 - 7 = 7 So SubArray = [7, 2, -3, 1]
         *
         * Now if we manage this as array we have to get difference
         * It's O(n^2).
         *
         * We can avoid this using a Dictionary or HashMap
         * Map<Sum, Frequency> sumFrequencyMap = new HashMap<>()
         */
        Map<Integer, Integer> sumFrequencyMap = new HashMap<>();
        sumFrequencyMap.put(0, 1); // Initially we have 0 as cumulative sum.
        int cumulativeSum = 0;
        int totalSubArray = 0;

        for (int i : nums) {
            cumulativeSum += i;
            if (sumFrequencyMap.containsKey(cumulativeSum - k)) {
                totalSubArray += sumFrequencyMap.get(cumulativeSum - k);
            }
            sumFrequencyMap.put(cumulativeSum, sumFrequencyMap.getOrDefault(cumulativeSum, 0) + 1);
        }
        return totalSubArray;
    }
}
