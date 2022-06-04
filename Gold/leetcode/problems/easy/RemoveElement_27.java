package com.leetcode.problems.easy;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveElement_27 {

    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{3,2,2,3}, 3));
    }

    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        int totalSwaps = 0;

//        if(nums.length == 0)
//            return 0;
//
//        if(nums.length == 1 && nums[0] == val) {
//            nums[0] = -val;
//            return 0;
//        }

        while(left <= right && right < nums.length) {
            if(nums[left] == val) {
                if(nums[right] == val) {
                    totalSwaps++;
                    right++;
                } else {
                    swap(nums, left++, right++);
                }
            } else {
                left++;
                right++;
            }
        }

        return nums.length - totalSwaps;

    }

    public static void swap(int [] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
