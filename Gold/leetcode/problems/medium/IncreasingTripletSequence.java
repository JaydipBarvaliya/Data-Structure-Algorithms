package com.leetcode.problems.medium;

/**
 * @author neeraj on 20/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IncreasingTripletSequence {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        System.out.println(increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
    }

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE; // this will keep track of the smallest value
        int second = Integer.MAX_VALUE; // this will keep track of the second smallest value

        for (int i : nums) {
            if (i <= first) {
                first = i; // if the current value is less than the smallest element, let's keep it
            } else if (i <= second) {
                second = i; // if the current value is greater than first but less than the second smallest value
            } else {
                return true; // if the current value is neither smaller than first smallest or the seoncd smallest
                // i.e this value is greatest than both.
            }
        }
        return false; // we didn't find any solution.
    }
}
