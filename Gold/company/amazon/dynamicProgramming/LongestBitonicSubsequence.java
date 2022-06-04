package com.company.amazon.dynamicProgramming;

public class LongestBitonicSubsequence {

    public static void main(String[] args) {
        printLongestBitonicSubsequence(new int[]{1, 11, 2, 10, 4, 5, 2, 1});
        printLongestBitonicSubsequence(new int[]{12, 11, 40, 5, 3, 1});
        printLongestBitonicSubsequence(new int[]{80, 60, 30, 40, 20, 10});
    }

    public static void printLongestBitonicSubsequence(int[] arr) {
        int N = arr.length;
        int[] LIS = new int[N];
        int[] LDS = new int[N];
        int[] prevLIS = new int[N];
        int[] nextLDS = new int[N];

        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            LDS[i] = 1;
            prevLIS[i] = -1;
            nextLDS[i] = -1;
        }

        // First find LIS
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                    prevLIS[i] = j;
                }
            }
        }

        // Now find LDS
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > 0; j--) {
                if (arr[i] > arr[j] && LDS[i] < LDS[j] + 1) {
                    LDS[i] = LDS[j] + 1;
                    nextLDS[i] = j;
                }
            }
        }

        // Now find the max Point
        int longestBitonicSubsequenceLength = 0;
        int maxPeakPoint = 0;
        for (int i = 0; i < N; i++) {
            if (longestBitonicSubsequenceLength < (LIS[i] + LDS[i] - 1)) {
                longestBitonicSubsequenceLength = LIS[i] + LDS[i] - 1;
                maxPeakPoint = i;
            }
        }

        System.out.print("Longest Bitonic Sub-sequence length is " + longestBitonicSubsequenceLength);
        printActualBitonicSubsequence(arr, prevLIS, nextLDS, maxPeakPoint);
    }

    private static void printActualBitonicSubsequence(int[] arr, int[] prevLIS, int[] nextLDS, int maxPeakPoint) {
        System.out.print(" and the actual sequence is [");

        // Print LIS
        printLIS(arr, prevLIS, maxPeakPoint);

        // Print LDS
        printLDS(arr, nextLDS, nextLDS[maxPeakPoint]);
        System.out.println("]");
    }

    private static void printLIS(int[] arr, int[] prevLIS, int index) {
        if (prevLIS[index] == -1) { // base condition
            System.out.print(arr[index] + ",");
            return;
        }
        printLIS(arr, prevLIS, prevLIS[index]);
        System.out.print(arr[index] + ",");
    }

    private static void printLDS(int[] arr, int[] nextLDS, int nextPoint) {
        if (nextPoint < 0) {
            return;
        }
        System.out.print(arr[nextPoint] + ",");
        printLDS(arr, nextLDS, nextLDS[nextPoint]);
    }
}
