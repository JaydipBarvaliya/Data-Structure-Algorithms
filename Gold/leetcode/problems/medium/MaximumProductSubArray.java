package com.leetcode.problems.medium;

/**
 * @author neeraj on 05/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{6, -3, -10, 0, 2}));
        System.out.println(maxProduct(new int[]{-1, -3, -10, 0, 60}));
        System.out.println(maxProduct(new int[]{-2, -3, 0, -2, -40}));
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
        System.out.println(maxProduct(new int[]{3, -1, 4}));
        System.out.println(maxProduct(new int[]{1, -3, -10, 0, -2}));
    }

    public static int maxProduct(int[] nums) {
        int i = 0;
        int j = 0;
        int PRODUCT = 0;
        int MAX_PRODUCT_SUM = Integer.MIN_VALUE;
        while (j < nums.length) {
            if(nums[j] == 0) {
                if( j - i > 1) {
                    while (i < j) {
                        PRODUCT /= nums[i++];
                        MAX_PRODUCT_SUM = Math.max(MAX_PRODUCT_SUM, PRODUCT);
                    }
                }
                PRODUCT = 0;
                j++;
                i = j;
            } else {
                if(PRODUCT == 0) {
                    PRODUCT = 1;
                }
                PRODUCT *= nums[j++];
            }
            MAX_PRODUCT_SUM = Math.max(MAX_PRODUCT_SUM, PRODUCT);
        }
        while (i < j-1) {
            PRODUCT /= nums[i++];
            MAX_PRODUCT_SUM = Math.max(MAX_PRODUCT_SUM, PRODUCT);
        }
        return MAX_PRODUCT_SUM;
    }
}
