package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * @author neeraj on 19/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountAllSubSequenceHavingProductLessThanK {

    public static void main(String[] args) {
        System.out.println(countOfAllSubsequence(new int[]{1, 2, 3, 4}, 10));
        System.out.println(countOfAllSubsequence(new int[]{4, 8, 7, 2}, 50));
        System.out.println(countOfAllSubsequence(new int[]{10, 5, 2, 6}, 100));
    }

    public static int countOfAllSubsequence(int[] arr, int k) {
        return solve(arr, arr.length - 1, k);
    }

    private static int solve(int[] arr, int N, int k) {
        if (N < 0) return 0;

        if (arr[N] <= k) {
            // Choose it
            return 1 + solve(arr, N - 1, k / arr[N])
                    + solve(arr, N - 1, k); // Or not choose it
        } else {
            return solve(arr, N - 1, k); // definitely not choosing it.
        }
    }
}
