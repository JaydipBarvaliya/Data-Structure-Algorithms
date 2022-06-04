package com.leetcode.year_2020.DP.combinatorics;

import java.util.Arrays;

public class NumberOfWaysToRearrangeSticksWithKSticksVisible {
    static int MOD = 1000000000 + 7;

    public static void main(String[] args) {
        System.out.println(rearrangeSticks(3, 3));
        System.out.println(rearrangeSticks(3, 2));
        System.out.println(rearrangeSticks(5, 5));
        System.out.println(rearrangeSticks(5, 1));
        System.out.println(rearrangeSticks(20, 11));
    }

    /**
     * Assume we have 5 sticks and we want to see 3 sticks from the left, so how many ways we can arrange it
     * *
     * *                                                 |
     * *                                      |          |
     * *                             |        |          |
     * * 		           |         |        |          |
     * * (*)       |       |         |        |          |
     * * ---------------------------------------------------
     * * You       1       2         3        4          5
     * *
     * *
     * If I select 3 as my first stick I can select remaining sticks from {4,5}, since 1 and 2 are smaller and we can't see them from left
     * So for (n, k) i need to know what is the tallest stick on the left is and then find out how many remaining smaller sticks
     * are there in the right, this is difficult to keep so much information in an optimal manner.
     * <p>
     * Instead, we just change the perspective and try to see the problem from the right, we know if I keep tallest stick to the right most side
     * then I can always see it, and now the problem will be shorten down to (n-1, k-1)*1 [Since there is only 1 person who can be the tallest]
     * <p>
     * Now if we choose to not keep the tallest item on the right then there is no way you can see this item from the left,
     * since it will be hidden from some other taller item from the left, and the problem reduces to
     * (n-1, k) * (n-1) [Why multiply (n-1) As there will be n-1 eligible candidates to be selected as the non-tallest]
     * <p>
     * So we simply have to just add these two possibilities to get out solution.
     * *
     */
    public static int rearrangeSticks(int n, int k) {
        long[][] dp = new long[1001][1001];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        return (int) rearrangeSticks(n, k, dp);
    }

    private static long rearrangeSticks(final int n, final int k, final long[][] dp) {
        if (k == 0 || n < k) {
            return 0; // since there will be zero arrangement where we can see either 0 greater elements or greater than n elements
        }
        if (k == n) return 1;

        if (dp[n][k] != -1) return dp[n][k];

        long totalWays = 0;

        // When we are choosing tallest element on the right
        totalWays = totalWays + rearrangeSticks(n - 1, k - 1, dp);

        // When we are choosing non-tallest element on the right
        totalWays = (totalWays + (rearrangeSticks(n - 1, k, dp) * (n - 1))) % MOD;


        return dp[n][k] = totalWays;
    }
}
