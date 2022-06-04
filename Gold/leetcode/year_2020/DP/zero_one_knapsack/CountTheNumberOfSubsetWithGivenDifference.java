package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * @author neeraj on 05/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class CountTheNumberOfSubsetWithGivenDifference {

    public static void main(String[] args) {
        System.out.println(getCountOfNumberOfSubSetWithGivenDifference(new int[]{1, 1, 2, 3}, 1));
        System.out.println(getCountOfNumberOfSubSetWithGivenDifference(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(getCountOfNumberOfSubSetWithGivenDifference(new int[]{1}, 2));
    }

    public static int getCountOfNumberOfSubSetWithGivenDifference(int[] set, int difference) {
        /**
         * This problem is similar to {@link MinimumSubsetDifference}, the only difference
         * is in that problem we wanted to divide the set into 2 subset such that
         * there difference of the sum is minimum
         *
         * |S1 - S2| = Minimum
         * this problem is asking count the subset where
         * |S1 - S2| = Given Difference,
         *
         * So essentially if we have to divide into S1 and S2
         * S1 = Sum(Set1)
         * S2 = Sum(Set2)
         *
         * where |S1 - S2| = difference
         *
         * Now , S1 - S2 = difference   --> Eq 1
         *       S1 + S2 = (Total_SUM)  --> Eq 2
         *    -----------------------------------
         *       2S1 + 0 = difference + Total_SUM
         *    ----------------------------------
         *
         *    So S1 = (difference + Total_SUM) / 2;
         *
         * So our Sub-problem divided into find any Subset whose sum is  (difference + Total_SUM) / 2;
         *
         * For Example : Set[] =  1, 1, 2, 3, difference = 1
         *
         * Total_SUM(Set) = 7
         * So S1 = (1 + 7)/2 = 4.
         *
         * This is now just the {@link CountOfSubsetSumWithAGivenSum} Problem, with
         * int countOfSubSetSum(set, 4).....
         */

        int TOTAL_SUM = 0;
        for (int i : set) {
            TOTAL_SUM += i;
        }
        return countOfSubSetSumTopDown(set, (difference + TOTAL_SUM) / 2);
    }

    private static int countOfSubSetSumTopDown(int[] set, int total_sum) {
        int dp[][] = new int[set.length + 1][total_sum + 1];

        // Initialize the matrix
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) { // No item in the cart, hence we can't make subset
                    dp[i][j] = 0;
                }
                if (j == 0) { // When we have to make sum 0, we can always take {} empty set.
                    dp[i][j] = 1;
                }
            }
        }

        // Now to populate rest of matrix.
        // i represent item in the set
        // j represent the totalSum.
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                if (set[i - 1] <= j) { // When item is less than sumToAchieve, so it can contribute.
                    dp[i][j] = dp[i - 1][j - set[i - 1]] + // Choose to contribute
                            dp[i - 1][j]; // Not Choose to contribute.
                } else { // when item > the sum to achieve
                    dp[i][j] = dp[i - 1][j]; // This will never contribute to make the subsetSum
                }
            }
        }
        return dp[set.length][total_sum];
    }
}
