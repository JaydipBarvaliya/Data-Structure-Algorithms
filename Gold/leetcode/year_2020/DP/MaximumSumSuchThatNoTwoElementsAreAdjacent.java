package com.leetcode.year_2020.DP;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 *
 * @author neeraj on 16/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumSumSuchThatNoTwoElementsAreAdjacent {

    public static void main(String[] args) {
        System.out.println(findMaxSum(new int[]{5, 5, 10, 100, 10, 5}));
        System.out.println(findMaxSum(new int[]{1, 2, 3}));
        System.out.println(findMaxSum(new int[]{1, 20, 3}));
        System.out.println(findMaxSum(new int[]{1, 2, 3, 1}));
        System.out.println(findMaxSum(new int[]{2, 7, 9, 3, 1}));


        LogUtil.logIt("Solving using Top Down Recursive approach.....");
        System.out.println(findMaxSumTopDown(new int[]{5, 5, 10, 100, 10, 5}));
        System.out.println(findMaxSumTopDown(new int[]{1, 2, 3}));
        System.out.println(findMaxSumTopDown(new int[]{1, 20, 3}));
        System.out.println(findMaxSumTopDown(new int[]{1, 2, 3, 1}));
        System.out.println(findMaxSumTopDown(new int[]{2, 7, 9, 3, 1}));

    }

    public static int findMaxSum(int[] nums) {
        /**
         * This problem is exactly {@link HouseRobber} problem.
         * Let's try to break this problem down into smallest valid possible input.
         *
         * 1) When there are no elements, maxSum will be 0.
         * 2) When there is just 1 element, No Choice we have to take it. so maxSum = nums[0].
         * 3) When there are 2 elements, Now we have a choice maxSum = Math.max(nums[0], nums[1]);
         * 4) When we have 3 elements
         *      [ 5, 100, 20]
         *      So we can either take (5+20) or (100)
         *      which is maxSum[3] = Max(nums[1] + nums[3], 100);
         *
         *      We can also write 100 as maxSum[2]... since we calculated maxSum[0] before
         *      and nums[1] as maxSum[3-2] in General ith Form
         */
        int[] maxSum = new int[nums.length];
        maxSum[0] = nums[0];
        maxSum[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 2] + nums[i], maxSum[i - 1]);
        }
        return maxSum[nums.length - 1];
    }

    static int T[]; // Memorization
    public static int findMaxSumTopDown(int[] nums) {
        /**
         * This problem is exactly {@link HouseRobber} problem.
         * Let's try to break this problem down into smallest valid possible input.
         *
         * 1) When there are no elements, maxSum will be 0.
         * 2) When there is just 1 element, No Choice we have to take it. so maxSum = nums[0].
         * 3) When there are 2 elements, Now we have a choice maxSum = Math.max(nums[0], nums[1]);
         * 4) When we have 3 elements
         *      [ 5, 100, 20]
         *      So we can either take (5+20) or (100)
         *      which is maxSum[3] = Max(nums[1] + nums[3], 100);
         *
         *      We can also write 100 as maxSum[2]... since we calculated maxSum[0] before
         *      and nums[1] as maxSum[3-2] in General ith Form
         */
        T= new int[nums.length + 1];
        return solveMaxSumUsingTopDown(nums, nums.length - 1);
    }

    public static int solveMaxSumUsingTopDown(int[] nums, int N) {

        // Base Condition
        if (N < 0) return 0;
        if (N == 0) return nums[N];
        if (N == 1) return Math.max(nums[N - 1], nums[N]);

        if(T[N] != 0) return T[N];
        return T[N] =  Math.max(solveMaxSumUsingTopDown(nums, N - 1), // Either take B from [A, B, C]
                nums[N] + solveMaxSumUsingTopDown(nums, N - 2)); // Either take A and C from [A, B, C]
    }
}
