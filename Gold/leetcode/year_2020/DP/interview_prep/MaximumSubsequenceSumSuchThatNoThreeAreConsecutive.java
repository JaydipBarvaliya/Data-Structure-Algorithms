package com.leetcode.year_2020.DP.interview_prep;

import com.leetcode.year_2020.DP.HouseRobber;

/**
 * @author neeraj on 07/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumSubsequenceSumSuchThatNoThreeAreConsecutive {

    public static void main(String[] args) {
        System.out.println(getMaximumSubsequenceSum(new int[]{1, 2, 3}));
        System.out.println(getMaximumSubsequenceSum(new int[]{3000, 2000, 1000, 3, 10}));
        System.out.println(getMaximumSubsequenceSum(new int[]{100, 1000, 100, 1000, 1}));
        System.out.println(getMaximumSubsequenceSum(new int[]{1, 1, 1, 1, 1}));
        System.out.println(getMaximumSubsequenceSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    public static int getMaximumSubsequenceSum(int[] arr) {
        /**
         * This problem is mainly an extension to {@link HouseRobber}
         * problem, here 3 consecutive numbers shouldn't contribute to maxSum.
         *
         * So the problem drills down to when we are at i+2.
         *
         * [i, (i)+ (i+1), i+2]
         *  0     1         2
         *
         * we can either take Max of (1, (0+2), (1+2)).
         * So for every i starting from index 2.
         * sum[i] = Max (sum[i-1], // Dropping ith item
         *               sum[i-2] + arr[i] // Dropping i-1th item.
         *               sum[i-3] + arr[i] + arr[i-1] // Dropping i-2th item.
         */

        int[] sum = new int[arr.length];

        // For just 1 item max-sum
        sum[0] = arr[0];

        // For 2 items.
        sum[1] = arr[0] + arr[1];

        // For Exactly 3 items
        sum[2] = Math.max(sum[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));

        for (int i = 3; i < arr.length; i++) {
            sum[i] = Math.max(sum[i - 1],
                    Math.max(sum[i - 2] + arr[i],
                            sum[i - 3] + arr[i] + arr[i - 1]
                    ));
        }
        return sum[sum.length - 1];
    }
}
