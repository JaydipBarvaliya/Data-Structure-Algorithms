package com.leetcode.year_2020.DP;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/house-robber/
 * <p>
 * This problem can also solve :
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 *
 * @author neeraj on 05/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class HouseRobber {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));

        /**
         * For Maximum sum such that no two elements are adjacent test cases
         */
        System.out.println(rob(new int[]{1, 2, 3}));
        System.out.println(rob(new int[]{5, 5, 10, 100, 10, 5}));
        System.out.println(rob(new int[]{1, 20, 3}));
        System.out.println(rob(new int[]{2, 1, 1, 2}));

        LogUtil.logIt("Include Exclude way", true);
        System.out.println(robWithIncludeExcludeStrategy(new int[]{1, 2, 3}));
        System.out.println(robWithIncludeExcludeStrategy(new int[]{5, 5, 10, 100, 10, 5}));
        System.out.println(robWithIncludeExcludeStrategy(new int[]{1, 20, 3}));
        System.out.println(robWithIncludeExcludeStrategy(new int[]{2, 1, 1, 2}));


    }

    public static int robWithIncludeExcludeStrategy(int[] nums) {
        /**
         * Best explanation Greedy : https://www.youtube.com/watch?v=VT4bZV24QNo
         * we have to rob the houses such that no two adjacent houses are robbed
         *
         * In "include" we fill the matrix in such a way that if we rob current index house.
         * what is the max money we can rob without breaking 2 adjacent house constraint.
         *
         * In exclude case, what max we can rob if we choose to ignore the current index house.
         *
         * *            5                   10                            10                                100                                    5
         * ---------------------------------------------------------------------------------------------------------------------------------------------------
         * Include |    5    |  Exclude[i-1] + 10          |      Exclude[i-1] + 10           |   Exclude[i-1] + 100               |   Exclude[i-1] + 5      |
         *------------------------------------------------------------------------------------------------------------------------------------------------------
         * Exclude |   0     |  Max[Include/Exclude (i-1)] |   Max[Include/Exclude (i-1)]     |   Max[Include/Exclude (i-1)]       |  Max[Include/Exclude (i-1)]
         * ---------------------------------------------------------------------------------------------------------------------------------------------------
         *
         **/

        int include = nums[0];
        int exclude = 0;
        for (int i = 1; i < nums.length; i++) {
            int nextIncl = exclude + nums[i]; // we can only include i if i-1 was not included
            int nextExcl = Math.max(include, exclude); // we can choose to ignore this ith house, but we will continue with the money we got robbing at i-1

            include = nextIncl;
            exclude = nextExcl;
        }
        return Math.max(include, exclude);
    }

    public static int rob(int[] nums) {
        /**
         * This problem is exactly similar to {@link MaximumSumSuchThatNoTwoElementsAreAdjacent}
         */
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max((nums[i] + dp[i - 2]), dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
