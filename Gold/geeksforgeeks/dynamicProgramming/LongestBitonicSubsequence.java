package com.geeksforgeeks.dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Get LIS from Left to Right
 * GET LIS from Right to Left
 */
public class LongestBitonicSubsequence {

    private static Set<Integer> actualSequenceSet = new HashSet<>();

    public static void main(String[] args) {
        longestBitonicSubsequence(new int[]{1, 11, 2, 10, 4, 5, 2, 1});
//        longestBitonicSubsequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
    }

    public static void longestBitonicSubsequence(int[] arr) {

        int[] LIS = new int[arr.length]; // Longest Increasing Sub-sequence
        int[] LDS = new int[arr.length]; // Longest Decreasing Sub-sequence
        int[] prevLIS = new int[arr.length]; // To Store actual LIS
        int[] nextLDS = new int[arr.length]; // To Store actual LDS

        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
            LDS[i] = 1;
            prevLIS[i] = -1;
            nextLDS[i] = -1;
        }

        // Let's calculate LIS
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (LIS[j] + 1 > LIS[i]) {
                        LIS[i] = LIS[i] + 1;
                        prevLIS[i] = j;
                    }
                }
            }
        }

        // Let's calculate LDS
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j] && LDS[j] + 1 > LDS[i]) {
                    LDS[i] = LDS[j] + 1;
                    nextLDS[i] = j;
                }
            }
        }

        // Longest Bitonic Sub-subsequence = Maximum of [LIS + LDS - 1]
        int longestSubsequence = 0;
        int maxIndex = 0;
        for (int i = 0; i < LIS.length; i++) {
            if (longestSubsequence < LIS[i] + LDS[i] - 1) {
                longestSubsequence = LIS[i] + LDS[i] - 1;
                maxIndex = i;
            }
        }
        System.out.println("\nLongest Bitonic Subsequence is " + longestSubsequence);

        // Let's print the actual bitonic sequence
        printLongestBitonicSubsequence(arr, prevLIS, nextLDS, maxIndex);
    }

    private static void printLongestBitonicSubsequence(int[] arr, int[] prevLIS, int[] nextLDS, int maxIndex) {

        System.out.print("And the sequence is ");
        printLIS(arr, prevLIS, maxIndex);

        printLDS(arr, nextLDS, maxIndex, 0);
    }

    private static void printLDS(int[] arr, int[] nextLDS, int i, int commonIndex) {
        if (i < 0) {
            return;
        }
        if (commonIndex > 0) {
            System.out.print(arr[i] + ",");
        }
        printLDS(arr, nextLDS, nextLDS[i], commonIndex + 1);
    }

    private static void printLIS(int[] arr, int[] prevLIS, int i) {
        if (prevLIS[i] == -1) {
            System.out.print(arr[i] + ",");
            return;
        }

        printLIS(arr, prevLIS, prevLIS[i]);
        System.out.print(arr[i] + ",");
    }
}
