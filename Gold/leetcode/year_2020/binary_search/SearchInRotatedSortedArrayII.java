package com.leetcode.year_2020.binary_search;

/**
 * @author neeraj on 29/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        System.out.println(search(new int[]{3, 1}, 1));
        System.out.println(search(new int[]{3, 1, 2}, 3));
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 3)); // Failure if you use simply SearchInRotatedSorted technique
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 1));
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }


    public static boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    public static boolean search(int[] nums, int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return true;

            // Left Side Sorted
            if (nums[low] < nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    return search(nums, low, mid - 1, target);
                } else {
                    return search(nums, mid + 1, high, target);
                }
            } else if (nums[low] > nums[mid]) { // Right Side is Sorted
                if (nums[mid] < target && target <= nums[high]) {
                    return search(nums, mid + 1, high, target);
                } else {
                    return search(nums, low, mid - 1, target);
                }
            } else {
                // nums[low] == nums[mid] ... Duplicate entry
                // but nums[low] != target. So let's just get rid of the duplicate entry.
                return search(nums, low + 1, high, target);
            }
        }
        return false;
    }
}
