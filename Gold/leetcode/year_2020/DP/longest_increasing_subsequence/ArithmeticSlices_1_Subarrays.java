package com.leetcode.year_2020.DP.longest_increasing_subsequence;

/**
 * https://leetcode.com/problems/arithmetic-slices/
 * Awesome explanation : https://www.youtube.com/watch?v=rSi4MpGEz1M
 */
public class ArithmeticSlices_1_Subarrays {

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(numberOfArithmeticSlices(new int[]{1}));
        System.out.println(numberOfArithmeticSlices(new int[]{2, 5, 9, 12, 15, 18, 22, 26, 30, 34, 36, 38, 40, 41}));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums.length <= 2) return 0;
        /**
         * An integer array is called arithmetic if it consists of at least three elements and
         * if the difference between any two consecutive elements is the same.
         *
         * Since we have to give subarrays and not subsequence it makes this problem easier as compared to it's variant of subsequences
         *
         * We will maintain an array which will tell you how many arithmetic subarrays end's at i
         */
        int[] subArrayCount = new int[nums.length];
        int totalSuchSubArrays = 0;
        // Since we have a  restrictions of min length 3;
        for (int i = 2; i < nums.length; i++) {
            if (2 * nums[i - 1] == nums[i - 2] + nums[i]) { // OR if(nums[i-1] - nums[i-2] == nums[i] - nums[i-1]) is same thing
                // Basically if we have Ai, Aj, Ak
                // Aj - Ai should be equal to Ak-Aj
                // Hence Aj - Ai = Ak - Aj
                // or 2Aj = Ak+Ai
                subArrayCount[i] = subArrayCount[i - 1] + 1;
                totalSuchSubArrays += subArrayCount[i];
            }
        }
        return totalSuchSubArrays;
    }
}
