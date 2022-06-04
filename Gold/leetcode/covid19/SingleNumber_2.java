package com.leetcode.covid19;

/**
 * https://leetcode.com/problems/single-number-ii/
 *
 * @author neeraj on 23/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SingleNumber_2 {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
        System.out.println(singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    public static int singleNumber(int[] nums) {
        /**
         ** https://www.youtube.com/watch?v=ny1tk1AkON8
         ** So as explained here..... we have to deal with items at each bit position.
         ** Also if a number is repeated thrice and other number is available just once.
         ** So we know that all the thrice repeated numbers combined in a particular bit position
         ** will contribute to either 3 0's or 3 1's and the remaining bit is for single item
         ** So either 3 * x + 1  = 0's or 3*x + 1 = 1's
         ** So for each bit position if i mod by 3.... i will just be left with the bit of number appearing just ones.
         **/
        // So for this approach we need to count the bits at every position
        // if it's divisible by 0 that means the non-repeated element has just 0 in ith position position
        // else it has 1 in ith position.

        // for each ith position
        int result = 0;
        for (int i = 0; i < 32; i++) { // Since we have 32 bits in an integer.

            int count = 0; // Sum of all set bits with 1.
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0) { // this is to check if ith bit is set.
                    count++;
                }
            }
            // If count is divisible by 3 then set jth bit to 0, else set to 1.
            result = result | ((count % 3) << i);
        }
        return result;
    }
}
