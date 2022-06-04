package com.leetcode.year_2020.array;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

    public static void main(String[] args) {
        solveThis(new int[]{1,3,2}); // Next should be 2, 1, 3
        solveThis(new int[]{5,1,1}); // Next should be 1,1,5
        solveThis(new int[]{2,3,1,3,3}); // Next should be 1,1,5
    }

    public static void solveThis(int []nums) {
        System.out.println("Next Permutation of "+ LogUtil.getArrayAsString(nums));
        nextPermutation(nums);
        print(nums);
    }

    public static void nextPermutation(int[] nums) {
        if(nums.length == 0 || nums.length == 1) return;

        int i = nums.length -1;

        while(i > 0 && nums[i-1] >= nums[i]) i--;

        i--; // Since now we are at the position which was smaller

        if(i < 0) {
            // Simply reverse the whole array
            reverse(nums, 0, nums.length-1);
        } else {
            // Find Smallest number greater than nums[i]
            // on Right side
            int indexOfSmallestGreater = findIndexOfSmallestGreaterThan(nums, i+1, nums.length-1, nums[i]);
            swap(nums, i, indexOfSmallestGreater);
            reverse(nums, i+1,nums.length -1);
        }
    }

    public static void print(int []nums) {
        for(int i: nums) {
            System.out.print(i+ ",");
        }
        System.out.println();
    }

    public static int findIndexOfSmallestGreaterThan(int[] nums, int start, int end, final int value) {
        // Binary search on decreasing array
        int indexOfSmallestGreater = start;
        while(start <= end) {
            int mid = start + ((end - start) / 2);

            if(nums[mid] > value) {
                indexOfSmallestGreater = nums[mid] <= nums[indexOfSmallestGreater] ? mid : indexOfSmallestGreater;
                start = mid+1;
            } else {
                end = mid -1;
            }
        }
        return indexOfSmallestGreater;
    }

    public static void swap(int []nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void reverse(int []nums, int start, int end) {
        while(start<end) {
            swap(nums, start++, end--);
        }
    }
}
