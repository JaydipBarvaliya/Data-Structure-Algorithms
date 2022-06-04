package com.leetcode.year_2020.prefix_sum_technique;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contiguous-array/
 * @author neeraj on 14/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1}));
        System.out.println(findMaxLength(new int[]{0, 1, 0}));
        System.out.println(findMaxLength(new int[]{0, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLength(new int[]{0, 1, 1, 0, 1, 1, 1, 0}));

        LogUtil.logIt("Hello", true);

        System.out.println(findMaxLengthOptimized(new int[]{0, 1}));
        System.out.println(findMaxLengthOptimized(new int[]{0, 1, 0}));
        System.out.println(findMaxLengthOptimized(new int[]{0, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLengthOptimized(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLengthOptimized(new int[]{0, 1, 1, 0, 1, 1, 1, 0}));
    }

    public static int findMaxLengthOptimized(int[] nums) {
        /**
         * This problem can be solved using Prefix Sum technique
         * with Map to store the sum of items in the number to see if we have seen this
         * sum before.
         *
         * Also we know prefix sum will keep on increasing if there are no negative numbers
         * hence in order to negate the effect of 1 we will consider 0 as -1;
         *
         * Also since it's contiguous Array we will consider just the 1st occurrence of item.
         */
        Map<Integer, Integer> sumPositionCount = new HashMap<>();
        sumPositionCount.put(0, -1); // Even before starting sum is always 0 and -1 is the imaginary index.
        int prefixSum = 0;
        int MAX_LENGTH = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i] == 0 ? -1 : 1;

            // Now if we notice something we are taking -1 for 0 and 1 for 1;
            /**
             * x = 2*(0) - 1 = -1
             * y = 2*(1) - 1 = 1
             *
             * So we can use
             * prefixSum += 2*nums[i] - 1;
            **/
            prefixSum += 2*nums[i] - 1;

            if (sumPositionCount.containsKey(prefixSum)) {
                MAX_LENGTH = Math.max(MAX_LENGTH, i - sumPositionCount.get(prefixSum));
            } else {
                sumPositionCount.put(prefixSum, i);
            }
        }
        return MAX_LENGTH;
    }

    /**
     * Solution Inspired by : https://www.youtube.com/watch?v=63ogoiDrd4g&feature=emb_title
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> sumToPositionCount = new HashMap<>();
        int maxLength = 0;

        // Initialize 0th position as -1.
        // Specifically for cases like this : [0,1]
        sumToPositionCount.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // Since we have to add 1 if nums[i] == 1;
            // and subtract -1 if nums[i] == 0;
//            if (nums[i] == 0) {
//                sum--;
//            } else {
//                sum++;
//            }

            // We can also use an equation instead
            // So if nums[i] =0 then 0*2 -1 = -1;
            // and if nums[i] = 1 then 1*2 -1 = 1;
            sum += nums[i] * 2 - 1;
            if (sumToPositionCount.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - sumToPositionCount.get(sum));
            } else {
                sumToPositionCount.put(sum, i);
            }
        }
        return maxLength;
    }
}
