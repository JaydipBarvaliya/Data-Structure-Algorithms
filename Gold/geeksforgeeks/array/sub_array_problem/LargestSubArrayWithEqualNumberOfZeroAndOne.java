package com.geeksforgeeks.array.sub_array_problem;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Arrays;

/**
 * Problem Number: 134
 * <p>
 * We will solve via Longest SubArray with Sum as Zero using Map and a Sum Array
 * but we have to do a little extension as to mark all zeros as -1 so that we have negative numbers for our Sum as Zero Problem
 */
public class LargestSubArrayWithEqualNumberOfZeroAndOne {

    public static void main(String[] args) {
        findLargestSubArray(new int[]{1, 0, 1, 1, 1, 0, 0});
        findLargestSubArray(new int[]{1, 1, 1, 1});
        findLargestSubArray(new int[]{0, 0, 1, 1, 0});
    }

    private static void findLargestSubArray(int[] arr) {
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);

        for (int i = 0; i < copyArr.length; i++) {
            copyArr[i] = arr[i] == 0 ? -1 : arr[i];
        }

        int[] largestSubArr = LargestSubArrayWithZeroSum.findLargestSubArrayWithZeroSum(copyArr);
        for(int i=0;i<largestSubArr.length;i++) {
            largestSubArr[i] = largestSubArr[i] == -1 ? 0 : 1;
        }
        ArrayRotation.printArray(largestSubArr);
    }
}
