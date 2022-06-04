package com.leetcode.year_2020.DP.unbounded_knapsack;

import com.leetcode.year_2020.DP.zero_one_knapsack.CountOfSubsetSumWithAGivenSum;

import java.util.Arrays;

/**
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class CoinChange_NumberOfWays {

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(3, new int[]{2}));
        System.out.println(change(4, new int[]{1, 2, 3}));
    }


    /**
     * This problem is similar to {@link CountOfSubsetSumWithAGivenSum}, the only difference is
     * here we can take the coin multiple time(this is unbounded Knapsack) since we have limited supply.
     */
    static int t[][];

    public static int change(int amount, int[] coins) {

        t = new int[coins.length + 1][amount + 1]; // Memorization matrix.
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return findNoOfWaysWeCanMakeTheAmount
                (coins, coins.length - 1, amount);
        // return noOfWaysTopDown(coins, amount);
    }

    public static int findNoOfWaysWeCanMakeTheAmount(int[] coins, int n, int amount) {
        if (amount == 0) return 1; // If we have to make the amount of 0, we can make it without choosing any coin.
        if (n < 0 || amount < 0)
            return 0; // If we have no coins or we have to make negative amount that's never possible.

        if (t[n][amount] > -1) return t[n][amount]; // Return from cache.

        if (coins[n] <= amount) { // This coin can contribute
            // to build up the total Amount.
            return t[n][amount] = findNoOfWaysWeCanMakeTheAmount(coins, n, amount - coins[n]) // Choose the coin, but we can again choose it, hence not reducing n
                    + findNoOfWaysWeCanMakeTheAmount(coins, n - 1, amount); // Not Choose the coin.

        } else {
            return t[n][amount] = findNoOfWaysWeCanMakeTheAmount(coins, n - 1, amount); // Not choosing the coin.
        }
    }

    public static int noOfWaysTopDown(int[] coins, int amount) {
        int dp[][] = new int[coins.length + 1][amount + 1]; // Bottom up matrix to hold

        // Initialize the matrix.
        // i represents coin and j represents amount, in any cell.
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) { // We don't have any coins.
                    dp[i][j] = 0;
                }
                if (j == 0) { // We have to achieve sum of 0, which can be made by not choosing any coin.
                    dp[i][j] = 1;
                }
            }
        }

        // Let's complete remaining matrix
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + // Choosing the coin
                            dp[i - 1][j]; // and not choosing the coin.
                } else {
                    dp[i][j] = dp[i - 1][j]; // not choosing the coin.
                }
            }
        }
        return dp[coins.length][amount];
    }
}
