package com.leetcode.year_2020;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 15/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ProductofArrayExceptSelf {

    public static void main(String[] args) {
        LogUtil.printArray(productExceptSelf(new int[]{1, 2, 3, 4}));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        Arrays.fill(output, 1);
        int previousProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            output[i] *= previousProduct;
            previousProduct *= nums[i];
        }

        previousProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= previousProduct;
            previousProduct *= nums[i];
        }
        return output;
    }
}
