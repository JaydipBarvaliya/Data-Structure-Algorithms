package com.leetcode.year_2020.binary_search;

/**
 * @author neeraj on 19/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search(new int[]{1, 3}, 3));
    }

    public static int search(int[] nums, int target) {
        return searchInRotatedSorted(nums, 0, nums.length - 1, target);
    }

    public static int searchInRotatedSorted(int[] arr, int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[low] <= arr[mid]) { // Left Side of the Array is Sorted.
                if (arr[low] <= target && target < arr[mid]) {
                    return searchInRotatedSorted(arr, low, mid - 1, target);
                } else {
                    return searchInRotatedSorted(arr, mid + 1, high, target);
                }
            } else { // Right Side is sorted.
                if (arr[mid] < target && target <= arr[high]) {
                    return searchInRotatedSorted(arr, mid + 1, high, target);
                } else {
                    return searchInRotatedSorted(arr, low, mid - 1, target);
                }
            }
        }
        return -1;
    }
}
