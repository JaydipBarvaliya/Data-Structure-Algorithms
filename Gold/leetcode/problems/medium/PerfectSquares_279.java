package com.leetcode.problems.medium;

import com.util.LogUtil;

/**
 * @author neeraj on 13/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PerfectSquares_279 {

    public static void main(String[] args) {
        solvePerfectSquares(12);
        solvePerfectSquares(13);
        solvePerfectSquares(43);
        solvePerfectSquares(3);
        solvePerfectSquares(4);
    }

    public static void solvePerfectSquares(int n) {
        LogUtil.logIt("Least number of perfect square numbers for " + n + " is " + numSquares(n));
    }

    /**
     * Dynamic Programming, Memorization
     */
    public static int numSquares(int n) {

        // We know that for n=0, the least number of perfect squares is 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - (j * j)]);
            }
        }
        return dp[n];
    }
}
