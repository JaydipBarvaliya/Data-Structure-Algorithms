package com.leetcode.year_2020.DP.longest_increasing_subsequence;

/**
 * @author neeraj on 17/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountNumberOfTeams {

    public static void main(String[] args) {
        System.out.println(numTeams(new int[]{1, 2, 3, 4}));
    }

    public static int numTeams(int[] rating) {
        /**
         * so we have to check
         * For arr[i] < arr[j] < arr[k] where i < j < k, and
         *  arr[i] > arr[j] > arr[k] where i < j < k.
         *
         * So we can focus on finding things for j....
         * 1) when taking increasing sequence arr[i] < arr[j] < arr[k]
         *    for j we can find smaller items in left and larger items in right.
         *    then total such combination wil be left * right
         *
         * 2) when taking decreasing sequence arr[i] > arr[j] > arr[k], we can do the same.
         * Final result will be (1) + (2)
         */
        int left = 0;
        int right = 0;
        int totalSuchCombination = 0;
        // checking for rating[i] < rating[j] < rating[k]
        for (int j = 1; j < rating.length - 1; j++) { // Starting j from 1 since we want some item in left.
            // and we are checking this for every j
            left = 0;
            right = 0;
            for (int i = 0; i < j; i++) { // From 0 to j
                if (rating[i] < rating[j]) left++; // we found an item smaller than item at index j.
            }
            for (int k = j + 1; k < rating.length; k++) {
                if (rating[j] < rating[k]) right++; // we found an item greater than item at ith index.
            }
            totalSuchCombination += left * right;
        }

        // checking for rating[i] > rating[j] > rating[k]
        for (int j = 1; j < rating.length - 1; j++) { // Starting j from 1 since we want some item in left.
            // and we are checking this for every j
            left = 0;
            right = 0;
            for (int i = 0; i < j; i++) { // From 0 to j
                if (rating[i] > rating[j]) left++; // we found an item greater than item at index j.
            }
            for (int k = j + 1; k < rating.length; k++) {
                if (rating[j] > rating[k]) right++; // we found an item smaller than item at ith index.
            }
            totalSuchCombination += left * right;
        }
        return totalSuchCombination;
    }
}
