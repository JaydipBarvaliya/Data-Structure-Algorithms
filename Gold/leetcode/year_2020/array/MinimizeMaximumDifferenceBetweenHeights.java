package com.leetcode.year_2020.array;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 30/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimizeMaximumDifferenceBetweenHeights {

    public static void main(String[] args) {
        getMinimumDifference(new int[]{1, 15, 10}, 6);
        getMinimumDifference(new int[]{1, 5, 15, 10}, 3);
        getMinimumDifference(new int[]{4, 6}, 10);
        getMinimumDifference(new int[]{6, 10}, 3);
        getMinimumDifference(new int[]{1, 10, 14, 14, 14, 15}, 6);
    }

    public static int getMinimumDifference(int[] arr, int k) {
        Arrays.sort(arr);
        int small = arr[0], big = arr[arr.length - 1];
        int MIN_DIFFERENCE = big - small;
        small += k;
        big -= k;

        for (int i = 1; i < arr.length - 1; i++) {

            int afterSubtraction = arr[i] - k;
            int afterAddition = arr[i] + k;

            // Since it's not better than big or small, lets skip this
            if (afterSubtraction >= small || afterAddition <= big)
                continue;

            if (big - afterSubtraction <= afterAddition - small) {
                small = afterSubtraction;
            } else {
                big = afterAddition;
            }
        }
        MIN_DIFFERENCE = Math.min(MIN_DIFFERENCE, big - small);
        LogUtil.logIt("Minimum Difference in " +
                LogUtil.getArrayAsString(arr) + " is " + MIN_DIFFERENCE);
        return MIN_DIFFERENCE;
    }
}
