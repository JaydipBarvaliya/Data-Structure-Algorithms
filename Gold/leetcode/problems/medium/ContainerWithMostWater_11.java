package com.leetcode.problems.medium;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ContainerWithMostWater_11 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(maxArea(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(maxArea(new int[]{3, 0, 0, 2, 0, 4}));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int minVal = Integer.MAX_VALUE;
        int total = Integer.MIN_VALUE;
        while(left < right) {
            minVal = Math.min(height[left], height[right]);
            total = Math.max(total, minVal * (right - left));
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return total;
    }
}
