package com.geeksforgeeks.dynamicProgramming;

import com.util.LogUtil;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * @author neeraj on 2019-05-08
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RangeSumQuery {

    public static int[] cacheSum;

    public static void main(String[] args) {
        rangeSumQuery(new int[]{1, 2, 3});
        sumRange(1, 2);

        rangeSumQuery(new int[]{-2, 0, 3, -5, 2, -1});
        sumRange(0, 2);
        sumRange(2, 5);
        sumRange(0, 5);
    }

    public static void rangeSumQuery(int[] nums) {
        LogUtil.logIt("Range Sum for");
        LogUtil.printArray(nums);
        prepareSumCache(nums);
    }

    private static void prepareSumCache(int[] nums) {
        cacheSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            cacheSum[i + 1] = cacheSum[i] + nums[i];
        }
    }

    public static void sumRange(int i, int j) {
        if (i < 0 || j >= cacheSum.length - 1) {
            throw new IllegalStateException("Range out of bounds");
        }
        int sum = cacheSum[j + 1] - cacheSum[i];
        LogUtil.logIt("SumRange(" + i + "," + j + ") ===> " + sum, true);
    }

}
