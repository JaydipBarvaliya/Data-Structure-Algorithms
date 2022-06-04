package com.leetcode.year_2020.prefix_sum_technique;

/**
 * 926. Flip String to Monotone Increasing
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/
 */
public class FlipStringToMonotonicallyIncreasing {

    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("00110"));
        System.out.println(minFlipsMonoIncr("010110"));
        System.out.println(minFlipsMonoIncr("00011000"));
    }

    public static int minFlipsMonoIncr(String s) {
        /**
         ** 1. Skip 0 until we encounter the first 1.
         ** 2. Keep track of number of 1's in onesCount(prefix)
         ** 3. Any 0 that comes after we encounter 1 can be a potential candiate for flip. keep track of flipCount.
         ** 4. if flipCount exceeds onesCount,(i.e we have more zeros to be flipped than ones)
         **.   --> So it's better to flip ones than zeros.
         **/
        int onesCount = 0;
        int flipCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (onesCount == 0) continue; // we are looking for the first one
                flipCount++;
            } else {
                onesCount++;
            }
            if (flipCount > onesCount) {
                flipCount = onesCount; // we will rather flip 0 instead of 1.
            }
        }
        // this can be either 0 count or 1 count,
        // depending on the number of zeros after 1st one is encountered
        return flipCount;
    }
}
