package com.leetcode.year_2020.DP.must_do_geeks_for_geeks;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/minimum-cost-to-reach-a-point-n-from-0-with-two-different-operations-allowed/
 *
 * @author neeraj on 04/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumCostToReachPointNFrom0 {

    public static void main(String[] args) {
//        System.out.println(getMinCost(1, 3, 4));
        System.out.println(getMinCost(9, 5, 1));
        System.out.println(getMinCost(8, 1, 4));
        System.out.println(getMinCost(7, 1, 5));
    }

    static int[] dp; // Memorize

    public static int getMinCost(int N, int P, int Q) {
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = P; // You can reach to 1st only from 0 so cost is P
        dp[2] = dp[1] + P; // Same you can't reach from 0 to 2 directly (since 0 * 2 = 0) so cost will be P
        return solve(P, Q, N);
    }

    /**
     * @param P == COST TO TAKE JUMP OF 1
     * @param Q == COST TO TAKE JUMP OF 2
     * @param N == CURRENT POSITION
     */
    private static int solve(int P, int Q, int N) {
        if (dp[N] != -1) return dp[N];

        if (N % 2 == 0) { // For Even Locations we have 2 choices
            // either we can come from N/2 and take jump of 2.
            // or come from (N-1) and take 2 step1.
            dp[N] = Math.min((solve(P, Q, N / 2) + Q),
                    (solve(P, Q, N - 1) + P));

        } else {
            // WE HAVE ONLY ONE CHOICE THAT WE COME FROM LAST
            dp[N] = 1 + solve(P, Q, N - 1);
        }
        return dp[N];
    }
}
