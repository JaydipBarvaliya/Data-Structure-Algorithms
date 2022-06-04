package com.leetcode.year_2020.prefix_sum_technique;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
 * <p>
 * Given an array containing only 0s and 1s, find the largest subarray which contains equal no of 0s and 1s. Expected time complexity is O(n).
 *
 * @author neeraj on 20/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LargestSubArrayWithEqualNumberOfZerosAndOnes {

    public static void main(String[] args) {
        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{
                0, 1, 1, 0, 1, 1, 1, 0
        }));

        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{
                1, 0, 1, 1, 1, 0, 0
        }));

        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{
                1, 1, 1, 1
        }));

        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{
                0, 0, 1, 1, 0
        }));



        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{0, 1}));
        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{0, 1, 0}));
        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{0, 0, 0, 0, 1, 1}));
        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
        System.out.println(findLargestSubArrayWithEqualNumberOfZerosAndOnes(new int[]{0, 1, 1, 0, 1, 1, 1, 0}));
    }

    public static int findLargestSubArrayWithEqualNumberOfZerosAndOnes(int[] nums) {
        /**
         * This is again a prefix sum technique. This is similar to {@link LargestSubArrayWithZeroSum}
         * the only catch here is we can consider "0" as "-1" while calculating prefixSum.
         * which will help us to neutralize the only 1's effect.
         */
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumFirstOccurrence = new HashMap<>();
        int LargestSubArray = 0;

        prefixSumFirstOccurrence.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i] == 0 ? -1 : 1;

            if (prefixSumFirstOccurrence.containsKey(prefixSum)) {
                LargestSubArray = Math.max(LargestSubArray, i - prefixSumFirstOccurrence.get(prefixSum));
            } else {
                prefixSumFirstOccurrence.put(prefixSum, i);
            }
        }
        return LargestSubArray;
    }
}
