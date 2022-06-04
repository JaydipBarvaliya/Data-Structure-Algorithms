package com.leetcode.year_2020.all_premium.tags.google.sort;

import com.util.LogUtil;

/**
 * https://www.lintcode.com/problem/508/
 */
public class WiggleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 2, 1, 6, 4};
        wiggleSort(nums);
        LogUtil.printArray(nums);
    }

    public static void wiggleSort(int[] nums) {
        // 3,5,2,1,6,4
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i + 1] < nums[i]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i + 1] > nums[i]) {
                    swap(nums, i, i + 1);
                }
            }
        }
        // 1,2,3,4
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
