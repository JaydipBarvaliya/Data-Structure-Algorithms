package com.leetcode.year_2020;

/**
 * @author neeraj on 29/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumInSortedRotatedArray {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(findMin(new int[]{1, 3}));
        System.out.println(findMin(new int[]{3, 1}));
        System.out.println(findMin(new int[]{1}));
        System.out.println(findMin(new int[]{1, 2, 3}));
        System.out.println(findMin(new int[]{3, 3, 1, 3}));
    }

    /**
     * This will not work for duplicates.
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;


        /**
         * Intuition behind it, Assume we are standing in the middle
         * Now since this is a rotated sorted array, and if we are standing in the middle
         * So if the item[middle] < item[end] [Sequence is increasing on the right side]
         * and inorder to come to the middle value it has to drop somewhere(you can't keep increasing to reach to a smaller value :P)
         * But since the last item on the right side is > items[middle] so drop must have happened in the leftSide.
         *
         * Similarly if item[middle] > items[end] .. [Sequence is decreasing on the right side]
         * So in this scenario inorder to circle back to some value drop is happening on the right side.
         *
         *
         * Example : item[middle] < item[end]
         * [100, 30, 40, 60, 70, 80, 90]  == Mid --> 60. [Drop happening on the left side]
         *
         * Example: item[middle] > item[end]
         * [4, 5, 6, 7, 0, 1, 2] == Mid --> 7 [Drop happening on the right side].
         */

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid; // Middle also can be a smallest item. that's why no (mid-1).
            }
        }
        if (nums.length > 0) {
            return nums[start];
        } else {
            return -1;
        }
    }
}
