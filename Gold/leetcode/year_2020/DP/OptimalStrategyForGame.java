package com.leetcode.year_2020.DP;

/**
 * @author neeraj on 25/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class OptimalStrategyForGame {

    public static void main(String[] args) {
        System.out.println(findMaximumPointGain(new int[]{8, 15, 3, 7}));
        System.out.println(findMaximumPointGain(new int[]{3, 9, 1, 2}));
        System.out.println(findMaximumPointGain(new int[]{20, 30, 2, 10}));
    }

    private static int findMaximumPointGain(int[] points) {
        /**
         * Now if i choose point[i] then obviously my opponent will choose such a number so that i loose
         * that's why instead of being greedy we need to check all possible combination.
         *
         * "Do your Best when you get the chance and expect the worse when things happen to you"
         *
         * So if i choose points[i] then my opponent will choose max of left items
         * and we will be left with
         *
         * option1 = point[i] + Math.min(f(i+2, j), f(i+1, j-1))
         * option2 = point[j] + Math.min(f(i+1, j-1), f(i, j-2))
         *
         * and we need Max(options1, option2);
         *
         * https://www.youtube.com/watch?v=ww4V7vRIzSk
         *
         * So we will solve it via 2D matrix and Fill it diagonally
         */
        int[][] dp = new int[points.length][points.length];
        for (int gap = 0; gap < points.length; gap++) {
            for (int i = 0, j = gap; j < points.length; i++, j++) {
                if (gap == 0) {
                    // if only 1 element is present you will take that element only
                    dp[i][j] = points[i];
                } else if (gap == 1) {
                    // if 2 are present we will choose the largest
                    dp[i][j] = Math.max(points[i], points[j]);
                } else {
                    // Okay now we have more than 2 elements, assume [25, 50, 2] .....which one should you pick, if you pick 25, the opponent will win
                    // hence we need to check picking both the ends
                    /**
                     * if you choose 1st (i.e 25 means ith value), then you will be left with
                     *
                     * arr[i] + Math.min(dp[i+2][j], dp[i+1][j-1]) : Why is that since we know other opponent will make sure pick the larger number from either end in his turn
                     *
                     * if you pick j
                     *
                     * arr[j] + Math.min(dp[i+1][j-1], dp[i][j-2])
                     */
                    int option1 = points[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int option2 = points[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    dp[i][j] = Math.max(option1, option2);
                }
            }
        }
        return dp[0][dp[0].length - 1];
    }
}
