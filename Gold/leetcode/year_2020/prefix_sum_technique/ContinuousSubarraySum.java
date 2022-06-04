package com.leetcode.year_2020.prefix_sum_technique;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 * <p>
 * SubArraySumMultipleOfK
 *
 * @author neeraj on 19/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        System.out.println(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        /**
         * Question says find a subarray of minimum size 2 that sums upto multiples of k.
         *
         * Input: [23, 2, 4, 6, 7],  k=6
         * Output: True
         * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
         *
         * Input: [23, 2, 6, 4, 7],  k=6
         * Output: True
         * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
         *
         * So we can use Prefix Sum Technique, with a twist since question is asking we have to check multiples of k.
         * Let's mod the prefixSum%k to keep them within limit of k.
         * After that we will use Map to check whether a runningSum%k already exist in Map if yes then we should return
         * true.
         *
         * Reason behind this is the Remainder Theorem.
         *  A % X == (A + (n*X)) % X; .... where n = 1 to ......infinity.
         *
         *  Proof:
         *
         *  10 % 6 == (10 + (1*6)) % 6 = 16%6 ==> 4 ==4
         *         == (10 + (2*6)) % 6 = 22%6 ==> 4 ==4
         *         == (10 + (3*6)) % 6 = 28%6 ==> 4 ==4
         *         == (10 + (4*6)) % 6 = 34%6 ==> 4 ==4
         *         == (10 + (8*6)) % 6 = 58%6 ==> 4 ==4
         *
         * For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the
         * remainders are [5,1,1,5,0]. We got remainder 5 at index 0 and at index 3. That means,
         * in between these two indexes we must have added a number which is multiple of the k. Hope this clarifies your doubt :)
         */
        int runningSum = 0;
        Map<Integer, Integer> runningSumPositionIndex = new HashMap<>();
        runningSumPositionIndex.put(0, -1); // Before the start of array sum is always 0.

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];

            if (k != 0) runningSum = runningSum % k; // This will keep all runningSum under limit of K

            // Have we seen this runningSum before.
            if (runningSumPositionIndex.containsKey(runningSum)) {
                if (i - runningSumPositionIndex.get(runningSum) > 1) { // This is where remainder theorem comes in play.
                    // and question asks us that size of sub-array should be at least 2
                    return true;
                }
            } else {
                runningSumPositionIndex.put(runningSum, i);
            }
        }
        return false;
    }
}
