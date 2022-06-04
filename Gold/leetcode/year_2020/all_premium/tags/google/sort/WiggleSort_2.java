package com.leetcode.year_2020.all_premium.tags.google.sort;

import com.util.LogUtil;

import java.util.Arrays;

public class WiggleSort_2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2, 1, 2, 1};
        wiggleSort(nums);
        LogUtil.printArray(nums);

        nums = new int[]{4, 4, 5, 5, 6, 6};
        wiggleSort(nums);
        LogUtil.printArray(nums);
    }

    public static void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        // Input
        // 4,5,4,6,5,4
        // Sorted : 4, 4, 5, 5, 6, 6
        //                |        |
        //                |        |
        //              Left      Right
        // Median Element
        int left = (nums.length + 1) / 2 - 1; // left will move in left // adding 1 in length to handle odd length
        int right = nums.length - 1; // ----------- Pointing to largest element
        // Right will also move in left
        // We will put median (left) in even index and largest element(right) in odd index

        // System.out.println(copy[left] + " and "+ copy[right]);
        // [1,5,1,1,6,4]
        // 1, 1, 1, 4, 5, 6
        // 1. 6. 1. 5. 1 4
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) { // Odd Index Largest
                nums[i] = copy[right--];
            } else {
                nums[i] = copy[left--];
            }
        }
    }
}
