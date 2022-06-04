package com.leetcode.covid19;

/**
 * https://leetcode.com/problems/single-number/
 *
 * @author neeraj on 02/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));


        System.out.println(solveUsingBitCheckTechnique(new int[]{2, 2, 1}));
        System.out.println(solveUsingBitCheckTechnique(new int[]{4, 1, 2, 1, 2}));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }

    /**
     * We can generalize it to be with any number. Just use MOD(%) with the repeated number given in question.
     */
    public static int solveUsingBitCheckTechnique(int[] nums) {

        // We know every number has appeared twice and just 1 number appeared once
        // Let's call that number x
        /**
         **  So essentially if the ith bit of x is set to 1, we will have (2*y + 1)
         **. number of One's in the ith bit. for all y>=0
         **
         ** Similarly if the ith bit of x is not set to 1 we will have (2*y + 1)
         ** number of Zeros's in the ith bit. for all y>=0
         **
         ** Now after calculating all Ones at every position if we found it to be even
         ** that means on that particular bit 0 will be set for "X" and at remaining "1"
         ** will be set.
         ** So our main job is to find One's at every position
         **/
        int result = 0;
        for (int i = 0; i < 32; i++) { // Since we have 32 bits in an integer.

            int count = 0; // Count of all bits set to 1
            for (int j : nums) {
                if ((j & (1 << i)) != 0) {  // Check if the ith bit is set or not
                    count++;
                }
            }
            // if count is even that means, we have to set 0 in the ith bit of X
            // else set 1
            // Formulae explanation, if count is even then count % 2 == 0
            // and result OR 0 << i; will give you 0th bit set.
            // if count is odd then count%2 == 1
            // and result OR 1 << i, will set 1 on the ith bit.
            result = result | ((count % 2) << i);
        }
        return result;
    }
}
