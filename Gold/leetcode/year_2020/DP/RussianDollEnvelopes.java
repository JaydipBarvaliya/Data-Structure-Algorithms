package com.leetcode.year_2020.DP;

import java.util.Arrays;

public class RussianDollEnvelopes {

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(maxEnvelopes(new int[][]{{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}}));
        System.out.println(maxEnvelopes(new int[][]{{10, 8}, {1, 12}, {6, 15}, {2, 18}}));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        int MAX = Integer.MIN_VALUE;
        Arrays.sort(envelopes, (b, a) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
//        LogUtil.printMultiDimensionArray(envelopes);
        // Now we can do just LIS
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (willPrevFit(envelopes[j], envelopes[i]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    MAX = Math.max(dp[i], MAX);
                }
            }
        }
        return MAX;
    }

    private static boolean willPrevFit(final int[] prev, final int[] curr) {
        return prev[0] < curr[0] && prev[1] < curr[1];
    }
}
