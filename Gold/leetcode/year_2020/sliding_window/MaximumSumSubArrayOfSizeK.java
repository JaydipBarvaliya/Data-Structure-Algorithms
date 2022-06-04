package com.leetcode.year_2020.sliding_window;

/**
 * https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumSumSubArrayOfSizeK {

    public static void main(String[] args) {
        System.out.println(getMaxSum(new int[]{4, 2, 1, 7, 8, 1, 2, 8, 1, 0}, 3));
        System.out.println(getMaxSum(new int[]{2, 1, 5, 1, 3, 2}, 3));
        System.out.println(getMaxSum(new int[]{2, 1, 5, 1, 3, 2}, 2));
    }

    public static int getMaxSum(int[] arr, int K) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i]; // Expanding the window.
            if (i - K + 1 >= 0) { // Mean now we have a window of size K
                // You can also rewrite i-K+1>=0 as (i >=k-1)
                maxSum = Math.max(maxSum, currentSum);

                // Now let's kick out the left-most item from the window.
                currentSum -= arr[i - K + 1];
            }
        }
        return maxSum;
    }
}
