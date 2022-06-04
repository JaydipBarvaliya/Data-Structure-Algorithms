package com.leetcode.problems.easy;

import com.geeksforgeeks.array.Rotate2DMatrix;
import com.util.LogUtil;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PlusOne_66 {

    public static void main(String[] args) {
//        LogUtil.printArray(plusOne(new int[]{1,2,3}));
//        LogUtil.printArray(plusOne(new int[]{1,0,0,9}));
        LogUtil.printArray(plusOne(new int[]{9,9,9}));
//        LogUtil.printArray(plusOne(new int[]{9,0,1,9}));
    }
    public static int[] plusOne(int[] digits) {
        for(int i=digits.length-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0;
        }

        int [] result = new int[digits.length + 1 ];
        result[0] = 1;
        return result;
    }

}
