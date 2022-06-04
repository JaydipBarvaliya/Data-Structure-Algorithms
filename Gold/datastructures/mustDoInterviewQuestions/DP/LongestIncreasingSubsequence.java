package com.datastructures.mustDoInterviewQuestions.DP;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/#arrays
 * Created by jaine03 on 22/07/17.
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(getLengthOfLIS(new int[]{10, 22, 9, 33, 21, 50, 41, 60}));
        System.out.println(getLengthOfLIS(new int[]{50, 3, 10, 7, 40, 80}));
        //System.out.println(maxTillNow);

        //System.out.println(getMaxSumIncreasingSubsequence(new int[]{1, 101, 2, 3, 100, 4, 5},7));

    }

    public static int maxTillNow = 1;

    /**
     * Recursive Solution
     *
     * @param array
     * @param n
     * @return
     */
    private static int getLengthOfLIS(int[] array, int n) {
        if (n == 1)
            return 1;

        int res, maxEndingHere = 1;
        for (int i = 1; i < n; i++) {

            res = getLengthOfLIS(array, i);
            if (array[i] > array[i - 1] && res + 1 > maxEndingHere) {
                maxEndingHere = res + 1;
            }
        }

        if (maxEndingHere > maxTillNow) {
            maxTillNow = maxEndingHere;
        }
        return maxEndingHere;
    }

    public static int getLengthOfLIS(int[] array) {
        int[] LIS = new int[array.length];

        Arrays.fill(LIS, 1);

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }
        Arrays.sort(LIS);
        return LIS[LIS.length - 1];
    }

    public static int getMaxSumIncreasingSubsequence(int[] array, int n) {
        int[] LIS = new int[n];

        // Set the LIS for each element
        for (int i = 0; i < array.length; i++) {
            LIS[i] = array[i];
        }

        // Now calculate the maxSum once
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && LIS[i] < LIS[j] + array[i]) {
                    LIS[i] = LIS[j] + array[i];
                }
            }
        }

        Arrays.sort(LIS);
        return LIS[LIS.length - 1];

    }
}
