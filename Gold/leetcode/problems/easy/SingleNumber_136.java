package com.leetcode.problems.easy;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SingleNumber_136 {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2,2,1}));
        System.out.println(singleNumber(new int[]{1,2,3,3,1,4,5,4,5}));
        System.out.println(singleNumber(new int[]{4,1,2,1,2}));
        System.out.println(singleNumber(new int[]{4,5,3,4,5,3,2}));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int i:nums) {
            result ^= i;
        }
        return result;
    }
}
