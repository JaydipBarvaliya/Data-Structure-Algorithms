package com.leetcode.year_2020.DP.longest_increasing_subsequence;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSequence {

    public static void main(String[] args) {
        System.out.println(longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8}));
        System.out.println(longestArithSeqLength(new int[]{3, 6, 9, 12}));
        System.out.println(longestArithSeqLength(new int[]{9, 4, 7, 2, 10}));
        System.out.println(longestArithSeqLength(new int[]{2, 4, 6, 8, 10}));
    }

    public static int longestArithSeqLength(int[] nums) {
        /**
         * Okay so when the length of nums <= 2, the longest seq can be just size of array
         */
        if (nums.length <= 2) return nums.length;

        /**
         * Now our actual meat of algorithm starts, what we will be doing is calculating the difference of pair(i, j)
         * for every i, we go from 0 to < i, and calculate the difference, if the difference is seen before then we just have to add the current item
         */
        final Map<Integer, Integer>[] tillI = new HashMap[nums.length]; // stores difference between i and j and their respective length
        int totalSuchArithmeticSubSequence = 0;

        // Initialize hashMap for all i's
        for (int i = 0; i < nums.length; i++) {
            tillI[i] = new HashMap<>();
        }
        int ans_max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int difference = nums[i] - nums[j];

                // if we didn't find the difference, i.e this jth item is seen for first time, so it will only contribute just 1
                int longestLengthTillI = tillI[j].getOrDefault(difference, 1);
                if (tillI[j].containsKey(difference)) {
                    totalSuchArithmeticSubSequence++;
                }
                ans_max = Math.max(ans_max, longestLengthTillI + 1); // why 1 since "i" is being added to the longest length till now
                tillI[i].put(difference, longestLengthTillI + 1);
            }
        }
        System.out.println("Total SuchSubsequence is "+ totalSuchArithmeticSubSequence);
        return ans_max;
    }
}
