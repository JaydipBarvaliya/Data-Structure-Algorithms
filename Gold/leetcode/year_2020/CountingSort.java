package com.leetcode.year_2020;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=pEJiGC-ObQE
 *
 * @author neeraj on 18/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 0, 0, 2, 5, 4, 0, 2, 8, 7, 7, 9, 2, 0, 1, 9};
        // Total Elements = 17
        // Distinct Element = 9.
        sort(arr, 9);
        LogUtil.printArray(arr);
    }

    public static void sort(int[] arr, int K) {
        // Step 1
        // Find out Distinct Elements also known as "K" in the given array it can be also given in the input
        // if not then we need to find that
        // Like put in Set which only keeps unique value and within O(N) time we know what K is

        int[] count = new int[K + 1]; // This will maintain our frequency of those K elements in the array.

        for (int i : arr) {
            count[i]++;
        }

        // Now we have the count array filled, we need to update this array such that we
        // know where the boundary of that kth element will be placed.
        // we can easily do this via Prefix Sum technique
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Now we know where we have to put the last element for each kth value, so let's put them
        // and simultaneously decrease their count in the count array, so that when another same kth element
        // comes in.
        int[] sortedArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArray[--count[arr[i]]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }
}
