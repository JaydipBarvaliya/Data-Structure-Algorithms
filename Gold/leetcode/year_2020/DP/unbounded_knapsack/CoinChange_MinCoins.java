package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.Arrays;

/**
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CoinChange_MinCoins {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange(new int[]{1, 2, 3}, 4));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{1,5,6,9}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{470, 18, 66, 301, 403, 112, 360}, 8235));
    }

    static int t[][]; // Memorization Cache

    public static int coinChange(int[] coins, int amount) {
        t = new int[coins.length + 1][amount + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        int minCoins = coinChangeMinimumNumbersOfCoinsRecursively(coins, coins.length - 1, amount);
        return minCoins != Integer.MAX_VALUE - 1 ? minCoins : -1;
    }

    public static int coinChangeMinimumNumbersOfCoinsRecursively(int[] coins, int n, int amount) {
        /**
         * Here we want to get minimumNumber of coins, so our base condition will differ from
         * {@link CoinChange_NumberOfWays} problem.
         */
        if (amount == 0) { // When we have to achieve amount = 0.
            return 0;
        }
        if (n < 0) { // When we have no items then for sure we can't achieve any sum, so minimum coins tends to INFINITY.
            return Integer.MAX_VALUE - 1;
        }

        if (t[n][amount] > -1) return t[n][amount];

        if (coins[n] <= amount) { // We can include this coin to achieve the amount.
            return t[n][amount] = Math.min(
                    // We choose the coin, added it's occurrence as 1, and now let's reduce the amount but not the "n" (since we have unlimited supply)
                    1 + coinChangeMinimumNumbersOfCoinsRecursively(coins, n, amount - coins[n]),
                    coinChangeMinimumNumbersOfCoinsRecursively(coins, n - 1, amount)
            );
        } else {
            return t[n][amount] = coinChangeMinimumNumbersOfCoinsRecursively(coins, n - 1, amount);
        }
    }
}
