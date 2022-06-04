package com.leetcode.problems.medium;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindPeakElement_162 {

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,3,1}));
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    public static int findPeakElement(int[] nums) {
        if(nums.length == 1)
            return 0;
        return binarySearch(nums, 0, nums.length -1);
    }

    public static int binarySearch(int []nums, int low, int high) {
        while(low <= high) {
            int mid = low + (high - low)/2;

            if(isPeak(nums, mid)) {
                return mid;
            }
            if((mid == 0) || (nums[mid] < nums[mid+1])) {
                return binarySearch(nums, mid+1, high);
            }
            if ((mid == nums.length -1) || (nums[mid] < nums[mid-1])) {
                return binarySearch(nums, low, mid-1);
            }
        }
        return -1;
    }

    public static boolean isPeak(int [] nums, int index) {
        if(index == 0) {
            return nums[0] > nums[1];
        }
        if(index == nums.length - 1) {
            return nums[index] > nums[index-1];
        }
        return nums[index] > nums[index-1] && nums[index] > nums[index+1];
    }
}
