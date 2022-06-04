package com.leetcode.year_2020.sliding_window;

/**
 * https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
 *
 * @author neeraj on 15/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SmallestSubArrayWithGivenSum {

    public static void main(String[] args) {
        System.out.println(getSmallestSubArrayLength(new int[]{1, 4, 45, 6, 0, 19}, 51));
        System.out.println(getSmallestSubArrayLength(new int[]{1, 10, 5, 2, 7}, 9));
        System.out.println(getSmallestSubArrayLength(new int[]{1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280));
        System.out.println(getSmallestSubArrayLength(new int[]{1, 2, 4}, 8));
    }

    public static int getSmallestSubArrayLength(int[] arr, int sum) {
        int currSum = 0;
        int begin = 0;
        int SMALLEST_WINDOW = Integer.MAX_VALUE;
        for (int end = 0; end < arr.length; end++) {
            currSum += arr[end];
            while (currSum > sum) {
                SMALLEST_WINDOW = Math.min(SMALLEST_WINDOW, end - begin + 1);
                currSum -= arr[begin];
                begin++;
            }
        }
        return SMALLEST_WINDOW;
    }
}
