package com.leetcode.year_2020.Greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class DivideArrayInKConsecitiveIntegers {

    public static void main(String[] args) {
        System.out.println(isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(isPossibleDivide(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(isPossibleDivide(new int[]{3, 3, 2, 2, 1, 1}, 3));
        System.out.println(isPossibleDivide(new int[]{1, 2, 3, 4}, 3));
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        int totalPartition = nums.length / k;

        Arrays.sort(nums);
        boolean[] status = new boolean[nums.length];
        int counter = 0, lastValue = 0;
        while (totalPartition-- > 0) {
            counter = 0;
            lastValue = 0;
            for (int i = 0; i < nums.length && counter < k; i++) {
                if (!status[i]) {
                    if (lastValue == 0 || nums[i] - lastValue == 1) {
                        counter++;
                        status[i] = true;
                        lastValue = nums[i];
                        if (counter == k) break;
                    } else if (nums[i] - lastValue > 1) {
                        return false;
                    }
                }
            }
        }
        return counter >= k;
    }
}
