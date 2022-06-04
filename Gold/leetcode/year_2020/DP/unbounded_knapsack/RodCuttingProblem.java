package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.Arrays;

/**
 * @author neeraj on 06/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RodCuttingProblem {

    public static void main(String[] args) {
        System.out.println(cutRodAndFindMaximumProfit(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                new int[]{1, 5, 8, 9, 10, 17, 17, 20},
                8
        ));

        System.out.println(cutRodAndFindMaximumProfit(
                new int[]{2, 3, 5},
                new int[]{2, 3, 5},
                11
        ));
    }

    static int t[][]; // Memorization and BottomUp Cache.

    public static int cutRodAndFindMaximumProfit(int[] cutLength, int[] price, int lengthOfRod) {
        /**
         * This problem is similar to 0/1 Knapsack, where we were given weight[], valueOfArray[], capacity of bag
         * and we have to choose the weights such that it's under capacity and maxProfit.
         *
         * Here also we have given price of each cut of rod.
         * |1|2|3|4|    <====== Cuts of pieceLength
         * |5|6|8|8|    <====== Price of respective cuts.
         * nd Length of Rod is 4.
         * Now the only difference from 0/1 here is that we can choose the same cut multiple times
         *
         * We can cut the rod in pieces of cutLength "1" 4 times, but in 0/1 Knapsack we can't choose the same weight multiple times.
         *
         * Hence this problem is of type unbounded knapsack.
         * So lets solve it.
         */
        t = new int[cutLength.length + 1][lengthOfRod + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
//        return cutRodAndFindMaximumProfitRecursively(price, cutLength, price.length - 1, lengthOfRod);
        return cutRodAndFindMaximumProfitBottomUp(price, cutLength, lengthOfRod);
    }

    public static int cutRodAndFindMaximumProfitRecursively(int[] price, int[] cutLength, int n, int lengthOfRod) {
        if (n < 0 || lengthOfRod <= 0)
            return 0; // We can't make any profit, if there is no rod to cut. (i.e lengthOfRod = 0).

        if (t[n][lengthOfRod] > -1) return t[n][lengthOfRod];

        if (cutLength[n] <= lengthOfRod) {
            // Making a cut of cutLength n, if you notice carefully we are not reducing n, since we can make the same cut again.
            return t[n][lengthOfRod] = Math.max((price[n] + cutRodAndFindMaximumProfitRecursively(price, cutLength, n, lengthOfRod - cutLength[n])),
                    cutRodAndFindMaximumProfitRecursively(price, cutLength, n - 1, lengthOfRod)); // Not Making a cut at all
        } else {
            return t[n][lengthOfRod] = cutRodAndFindMaximumProfitRecursively(price, cutLength, n - 1, lengthOfRod); // Not Making a cut at all
        }
    }

    public static int cutRodAndFindMaximumProfitBottomUp(int[] price, int[] cutLength, int lengthOfRod) {
        /**
         * This matrix's cell will represent, max profit
         * at with the givenCut till that cell and lengthOfRod represented by columns
         */
        int[][] dp = new int[cutLength.length + 1][lengthOfRod + 1];

        // Initialization of Matrix
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0) {  // First Row represent, we don't have any cuts to make
                    dp[i][j] = 0;
                }
                if (j == 0) {  // RodLength is 0, we can't make any profit.
                    dp[i][j] = 0;
                }
            }
        }

        // Populating rest of the matrix
        for (int i = 1; i < dp.length; i++) { // Represents cuts
            for (int j = 1; j < dp[i].length; j++) { // Represents lengthOfRod

                if (cutLength[i - 1] <= j) { // Cut left is <= RodLength
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - cutLength[i - 1]],
                            dp[i - 1][j]);
                } else {  // Cut left is greater than the rod's length itself.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[price.length][lengthOfRod];
    }
}
