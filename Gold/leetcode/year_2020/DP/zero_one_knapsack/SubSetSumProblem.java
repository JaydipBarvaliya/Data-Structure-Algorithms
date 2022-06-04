package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 *
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class SubSetSumProblem {

    public static void main(String[] args) {
        isSubSetSum(new int[]{2, 3, 7, 8, 10}, 16);
        isSubSetSum(new int[]{2, 3, 7, 8, 10}, 23);
        isSubSetSum(new int[]{2, 3, 7, 8, 10}, 2);
        isSubSetSum(new int[]{2, 3, 7, 8, 10}, 29);
    }

    static Boolean t[][]; // Memorization Matrix;
    static boolean dp[][]; // Bottom Up Cache.

    public static boolean isSubSetSum(int[] set, int sum) {
        t = new Boolean[set.length + 1][sum + 1];
        boolean result = isSubSetSum(set, set.length - 1, sum);
        System.out.println("Recursive with Memorization  for Sum " + sum + " is " + result);
//        dp = new boolean[set.length + 1][sum + 1];
//        System.out.println("Bottom Up Approach  " + isSubSetSumBottomUp(set, set.length - 1, sum));
        return result;
    }


    public static boolean isSubSetSum(int set[], int n, int sum) {
        // Base Condition
        if (sum == 0) return true; // we have reached to a level where
        // we have selected some elements which sums upto "SUM"

        if (n < 0 || sum < 0) return false;

        // Memorization
        if (t[n][sum] != null) return t[n][sum];

        // Choice
        if (set[n] <= sum) { // Item is less<= to the Sum, so might be included
            return t[n][sum] = isSubSetSum(set, n - 1, sum - set[n]) || // Choose
                    isSubSetSum(set, n - 1, sum); // Not choose.
        } else { // Item is larger so definitely not getting included
            return t[n][sum] = isSubSetSum(set, n - 1, sum); // we are not deducing the sum, since item didn't contribute.
        }
    }

    private static boolean isSubSetSumBottomUp(int set[], int n, int sum) {

        // So in Bottom up we have to do the initialization of the matrix.
        // As per Recursive. if(n < 0 || sum < 0) return false;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                }
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        // Now fill the rest of matrix.
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (set[i - 1] <= j) { // Item is <= sum
                    dp[i][j] = dp[i - 1][j - set[i - 1]] || dp[i - 1][j];
                } else { // Item is > sum, so definitely not choosing it.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[t.length - 1][dp[0].length - 1];
    }

}
