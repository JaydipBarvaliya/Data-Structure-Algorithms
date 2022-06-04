package com.leetcode.year_2020.DP.zero_one_knapsack;

import java.util.Arrays;

/**
 * @author neeraj on 05/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountOfSubsetSumWithAGivenSum {

    public static void main(String[] args) {
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{2, 3, 5, 6, 8, 10}, 10));
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{1, 2, 3, 3}, 6));
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{1, 1, 1, 1}, 2));
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{1, 1, 1, 1}, 1));
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{1, 1, 1}, 2));
        System.out.println(countOfSubSetSumWithGivenSum(new int[]{1, 2, 3}, 3));
    }

    static int t[][]; // Memorization and also for DP

    public static int countOfSubSetSumWithGivenSum(int[] set, int sum) {
        t = new int[set.length + 1][sum + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
//        return countOfSubSetSumWithGivenSum(set, set.length - 1, sum);
        return countOfSubSetSumWithGivenSumTopDown(set, sum);
    }

    private static int countOfSubSetSumWithGivenSum(int[] set, int n, int sum) {
        if (sum == 0) return 1; // when the sum we have to make is just 0, then we can have 1 empty subset.
        if (n < 0 || sum < 0) return 0; // Either of one is <0 there is no way we can make even a single subset.

        //cache
        if (t[n][sum] > -1) return t[n][sum];

        if (set[n] <= sum) {
            // Why Addition, since we have to calculate all subset not just one, so we have to explore both
            // path
            /**
             *                    [item] <= sum
             *                      /\
             *                     /  \
             *                    /    \
             *              [choose]  [not-choose-the-item]
             */
            return t[n][sum] = countOfSubSetSumWithGivenSum(set, n - 1, sum - set[n]) +  // Choosing the current item from set.
                    countOfSubSetSumWithGivenSum(set, n - 1, sum); // Not choosing the current item from set.
        } else {
            return t[n][sum] = countOfSubSetSumWithGivenSum(set, n - 1, sum);
        }
    }

    private static int countOfSubSetSumWithGivenSumTopDown(int[] set, int sum) {
        // First see the recursive solution we have to initialize the matrix based on the base condition
        // I --> represent items in the set(Rows)
        // J --> represents the sum.(Columns)
        int[][] dp = new int[set.length + 1][sum + 1];

        /**
         * Only difference between  subsetSum problem and this is in subSet sum we just have to return [true(sumExist)|false(SumDoesNotExist)]
         * here we have to return total number of subset.
         */
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0) { // When we have no item, we can't make any subset
                    dp[i][j] = 0;
                }
                if (j == 0) { // When we have to make 0 sum, that's always possible by taking just empty subset {}
                    dp[i][j] = 1;
                }
            }
        }

        // Now choice to populate remaining matrix;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (set[i - 1] <= j) { // Item is less than current sum.
                    dp[i][j] = dp[i - 1][j - set[i - 1]] + dp[i - 1][j];
                } else { // Current Item is greater than the current sum to achieve, hence not including it.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[set.length][sum];
    }

}
