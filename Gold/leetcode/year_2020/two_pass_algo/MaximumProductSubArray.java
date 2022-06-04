package com.leetcode.year_2020.two_pass_algo;

import com.datastructures.array.LargestSumContiguousSubArray;

/**
 * @author neeraj on 14/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-3, -2, -1}));
        System.out.println(maxProduct(new int[]{-1, -2, -3}));
        System.out.println(maxProduct(new int[]{-2}));
        System.out.println(maxProduct(new int[]{1, 0, -3, 4, -6, -5, 9, 0, 2}));
    }

    public static int maxProduct(int[] nums) {
        /**
         * This problem is similar to {@link LargestSumContiguousSubArray}
         * and will be solved exactly via Kadane's Algorithm.
         */
        int MAX_TILL_NOW = Integer.MIN_VALUE;
        int MAX_ENDING_HERE = 1;

        // we will do the 2 pass
        // 1st pass when we have even negative numbers
        // so eventually multiplying them will become positive
        for (int i : nums) {
            MAX_ENDING_HERE *= i;
            MAX_TILL_NOW = Math.max(MAX_TILL_NOW, MAX_ENDING_HERE);
            if (MAX_ENDING_HERE == 0) {
                MAX_ENDING_HERE = 1;
            }
        }

        // 2nd pass to get them from last
        // this is required if negative numbers are "ODD".
        // which will eventually give negative result from left side
        // so we want to also do a check from right side of the array if there is any bigger number available.
        //For Example: [1,0,-3,4,-6,-5,9,0,2]
        //Between the two zeroes, first pass will give ((-3)4(-6)) as max product and second pass will give
        // 9*(-5)*(-6)*4 as max-product and result will update maximum of them.
        MAX_ENDING_HERE = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            MAX_ENDING_HERE *= nums[i];
            MAX_TILL_NOW = Math.max(MAX_TILL_NOW, MAX_ENDING_HERE);
            if (MAX_ENDING_HERE == 0) {
                MAX_ENDING_HERE = 1;
            }
        }
        return MAX_TILL_NOW;
    }
}
