package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));
        System.out.println(canPartition(new int[]{1, 5, 4, 11, 5}));
    }

    static Boolean t[][]; // Memorization matrix.
    static boolean dp[][]; // Memorization matrix.

    public static boolean canPartition(int[] nums) {
        /**
         * This problem is similar to Subset Sum, the only difference is we have to parition the
         * set in such a way that each partition sum is exactly half of total sum.
         *
         * So first calculate the total sum we have to achieve
         *
         * Interesting Fact Recursive solution works faster than Bottom Up Approach in this problem.
         */
        int sumToAchieve = 0;
        for (int i : nums) {
            sumToAchieve += i;
        }
        if (sumToAchieve % 2 != 0) return false;
        sumToAchieve /= 2; // Divide into half since problem asks to partition equal subset sum
        t = new Boolean[nums.length + 1][sumToAchieve + 1];
        return canPartitionRecursively(nums, nums.length - 1, sumToAchieve);

        // For Top Down Approach.
//        dp = new boolean[nums.length + 1][sumToAchieve + 1];
//        return canPartitionTopDown(nums, nums.length - 1, sumToAchieve);
    }

    public static boolean canPartitionRecursively(int[] nums, int n, int sumToAchieve) {
        // Base Condition.
        if (sumToAchieve == 0)
            return true; // We don't have any sum to achieve so it can be done by not choosing any item.
        if (n < 0 || sumToAchieve < 0) return false; // we have to achieve sum with either no items in set
        // or we have to achieve a negative sum both the case are not possible
        // So let's return it.

        // Let's check our cache
        if (t[n][sumToAchieve] != null) return t[n][sumToAchieve];

        // Choice.
        if (nums[n] <= sumToAchieve) {  // item can contribute to the sum.
            return t[n][sumToAchieve] = canPartitionRecursively(nums, n - 1, sumToAchieve - nums[n]) // Choose the item
                    || canPartitionRecursively(nums, n - 1, sumToAchieve); // Or Don't choose the item, hence not reducing that item from sum.
        } else { // Item can't contribute to the sum.
            return t[n][sumToAchieve] = canPartitionRecursively(nums, n - 1, sumToAchieve);
        }
    }

    public static boolean canPartitionTopDown(int[] nums, int n, int sumToAchieve) {
        // So in Bottom up we have to do the initialization of the matrix.
        // As per Recursive. if(n < 0 || sum < 0) return false;
        // Also if our Sum == 0 if we have to make the sum = 0;
        // we can choose empty set to make 0 sum.

        // i represents all items in the set.
        // j represents sumToAchieve.
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) { // This represents when we don't have any item in the set.
                    dp[i][j] = false;
                }
                if (j == 0) { // When we have to achieve 0 sum.
                    dp[i][j] = true;
                }
            }
        }

        // Choice.
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                // Now we have to make choice based on item.
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
