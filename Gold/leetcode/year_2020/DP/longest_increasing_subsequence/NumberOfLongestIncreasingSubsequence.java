package com.leetcode.year_2020.DP.longest_increasing_subsequence;

/**
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * @author neeraj on 17/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberOfLongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
        System.out.println(findNumberOfLIS(new int[]{100, 90, 80, 70, 60, 50, 60, 70, 80, 90, 100}));
        System.out.println(findNumberOfLIS(new int[]{1, 2, 2, 2, 4, 4, 4, 7}));
    }

    public static int findNumberOfLIS(int[] nums) {
        int[] LIS = new int[nums.length];
        int[] count = new int[nums.length];
        int longestLength = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            LIS[i] = count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (LIS[i] == LIS[j] + 1) { // I am encountering the same longest increasing length again, so increment it's freq.
                        count[i] += count[j];
                    }
                    if (LIS[i] < (LIS[j] + 1)) {
                        LIS[i] = LIS[j] + 1;
                        count[i] = count[j]; // Copying the count of that J with combination of which this i will become longest.
                    }
                }
            }
            if (longestLength == LIS[i])
                result += count[i]; // If current LIS is contributing to the max, let's increase the result.
            else if (longestLength < LIS[i]) {// We found another LIS which is greater than the current one.
                longestLength = LIS[i];
                result = count[i];
            }
        }
        return result;
    }
}
