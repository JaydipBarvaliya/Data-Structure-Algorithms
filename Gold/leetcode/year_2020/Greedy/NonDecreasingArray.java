package com.leetcode.year_2020.Greedy.interval_scheduling;

/**
 * https://leetcode.com/problems/non-decreasing-array/
 * <p>
 * Given an array nums with n integers, your task is to check if it could become
 * non-decreasing by modifying at most 1 element.
 * <p>
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 * @author neeraj on 22/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NonDecreasingArray {

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4, 2, 3}));  //  case where i-2 is < 0..
        System.out.println(checkPossibility(new int[]{4, 2, 1}));  // False case.
        System.out.println(checkPossibility(new int[]{3, 4, 2, 3})); // case where i-2th element > ith element
        System.out.println(checkPossibility(new int[]{1, 7, 3, 5}));
        System.out.println(checkPossibility(new int[]{4, 7, 3, 9}));
    }

    public static boolean checkPossibility(int[] nums) {
        /**
         * This is a greedy problem, and we have 2 choices whenever we get nums[i] < nums[i-1]
         *
         * 1. To make nums[i-1] = nums[i].  This we can apply only when nums[i-2] < nums[i].
         * 2. To make nums[i] = nums[i-1].  This we can apply only when nums[i-2] > nums[i]
         *
         * For Case 1:
         *                   7
         *                  / \    5
         *                 /   \  /
         *                /     3
         *               /
         *              1
         * For Case 2:                         9
         *                     7             /
         *                    / \           /
         *                   /   \        /
         *                  /     \     /           ======> Here if i make 7 =3 the sequence is still invalid, since 4 is still > 3.
         *                 4       \   /            ======> Hence i have to make 3 = 7 so the sequence will become [4,7,7,9]
         *                          \/
         *                          3
         *
         *
         * Now we have to check only upto n-2th element why because we have already validated all elements before that
         * and that's interesting thing about greedy approach we never change our decisions once we take them.
         *
         */
        int numberOfTimesWeAreAllowedToMakeChange = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {  // Current item smaller than previous item... breaking the increasing(non-decreasing) seq.
                numberOfTimesWeAreAllowedToMakeChange++;

                if (i - 2 < 0 || nums[i - 2] < nums[i]) { //if we are on 1st index i-2 will give out of bounds.
                    // Hence (i-2) < 0 check.
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return numberOfTimesWeAreAllowedToMakeChange <= 1;
    }
}
