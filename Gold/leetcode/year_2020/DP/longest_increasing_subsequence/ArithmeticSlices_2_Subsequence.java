package com.leetcode.year_2020.DP.longest_increasing_subsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Awesome Explanation: https://www.youtube.com/watch?v=XjLT4TaXsgw
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 */
public class ArithmeticSlices_2_Subsequence {

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7}));
        System.out.println(numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums.length <= 2) return 0;
        /**
         * So we will use an array of HashMap and we will store
         * <difference: countOfSubsequence ending at i(with elements >= 2)>
         *     https://www.youtube.com/watch?v=XjLT4TaXsgw
         * How many ArithmeticSlices ends at i with length >= 2, we store that.
         */
        Map<Integer, Integer>[] differenceAndSubsequenceCount = new HashMap[nums.length];
        // Initializing
        for (int i = 0; i < nums.length; i++) {
            differenceAndSubsequenceCount[i] = new HashMap<>();
        }
        int totalCount = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                long difference = nums[i] - (long) nums[j];
                // Handling special case [0,2000000000,-294967296] (-Infinity, +Infinity) now you can't add a 3rd element to it
                // Assume -128 and 128 be the min and max limit of integer
                // So 128 - (-128) will be 256 which can't be stored
                if (difference <= Integer.MIN_VALUE || difference >= Integer.MAX_VALUE) {
                    continue;
                }

                int apsEndingAtJ = differenceAndSubsequenceCount[j].getOrDefault((int) difference, 0);
                int apsEndingAtI = differenceAndSubsequenceCount[i].getOrDefault((int) difference, 0);

                totalCount += apsEndingAtJ; // Why only adding j in the answer, since when I am calculating apsEndingAtI, I have just 2 elements, i itself and the jth loop
                // But if we had any apsEndingAtJ, then if the difference nums[i] - nums[j] is same as those apsEnding then this i makes it 3 length one hence added in answer/
                // Why +1 since ith difference with j was found and i is contributing to that difference
                differenceAndSubsequenceCount[i].put((int) difference, (apsEndingAtI + apsEndingAtJ + 1));
            }
        }
        return totalCount;
    }
}
