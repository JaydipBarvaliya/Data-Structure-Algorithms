package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * <p>
 * Same problem....
 * https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
 * <p>
 * https://www.geeksforgeeks.org/count-number-of-ways-to-cover-a-distance/
 *
 * @author neeraj on 07/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CombinationSum_NumberOfWays {

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{2, 3, 7}, 12));
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));

        // https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
        System.out.println(combinationSum4(new int[]{3, 5, 10}, 20));
        System.out.println(combinationSum4(new int[]{3, 5, 10}, 13));

        //https://www.geeksforgeeks.org/count-number-of-ways-to-cover-a-distance/
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 3));
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));

    }

    static int[][] T; // Memorization

    public static int combinationSum4(int[] nums, int target) {
        /**
         * This problem is exact replica of {@link CoinChange_NumberOfWays} and is a unbounded Knapsack problem.
         */
        T = new int[nums.length + 1][target + 1];
        for (int[] row : T) {
            Arrays.fill(row, -1);
        }
        return findNumberOfWays(nums, target, nums.length - 1);
    }

    private static int findNumberOfWays(int[] nums, int target, int n) {
        if (target == 0) return 1; // If we have to achieve 0 score there is just 1 way, don't pick any point
        if (n < 0 || target < 0) return 0; // we can never make negative points.


        // Return from cache
        if (T[n][target] != -1) return T[n][target];

        if (nums[n] <= target) { // When our points <= score, we can either choose it or don't
            // why we are not decrementing n since we know that we have unlimited supply of points.
            return T[n][target] = findNumberOfWays(nums, target - nums[n], n)
                    + findNumberOfWays(nums, target, n - 1);
        } else {
            // Points > score, so we are definitely not choosing it.
            return T[n][target] = findNumberOfWays(nums, target, n - 1);
        }
    }
}
