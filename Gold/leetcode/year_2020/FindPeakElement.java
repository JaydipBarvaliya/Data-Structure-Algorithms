package com.leetcode.year_2020;

/**
 * @author neeraj on 11/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
    }

    public static int findPeakElement(int[] nums) {
        return findPeakElement(nums, 0, nums.length - 1);
    }

    public static int findPeakElement(int[] nums, int low, int high) {
        while (low <= high) {
            int middle = low + (high - low) / 2;

            int leftElement = middle == 0 ? Integer.MIN_VALUE : nums[middle - 1];
            int rightElement = middle == nums.length - 1 ? Integer.MIN_VALUE : nums[middle + 1];

            if (nums[middle] > leftElement && nums[middle] > rightElement) {
                return middle;
            }

            if (nums[middle] < rightElement) {
                return findPeakElement(nums, middle + 1, high);
            } else {
                return findPeakElement(nums, low, middle - 1);
            }
        }
        return -1;
    }
}
