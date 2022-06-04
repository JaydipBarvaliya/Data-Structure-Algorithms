package com.geeksforgeeks.array;

import com.util.LogUtil;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int arr[] = {50, 3, 10, 7, 40, 80};
        System.out.println(getLengthOfLis(arr));

        arr = new int[]{-1, 3, 4, 5, 2, 2, 2, 2};
        System.out.println(getLengthOfLis(arr));

        System.out.println("Now checking LIS greater than specific length");
        arr = new int[]{0, 4, 12, 2, 10, 6, 9, 13, 3, 11, 7, 15};
        printLISOfSpecificLength(arr, 2);
    }

    public static int getLengthOfLis(int[] arr) {
        int[] LIS = new int[arr.length];
        int[] actualSequence = new int[arr.length];

        Arrays.fill(LIS, 1);

        // Initially every index has LIS = 1
        for (int i = 0; i < arr.length; i++) {
            actualSequence[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {

                if (arr[i] >= arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                    actualSequence[i] = j;
                }
            }
        }

        LogUtil.printArray(actualSequence);

        // Find the index of maximum LIS
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < LIS.length; i++) {
            if (max < LIS[i]) {
                max = LIS[i];
                maxIndex = i;
            }
        }

        // Now Let's Print Out the Actual Sequence which was involved in LIS
        int indexOne;
        int indexTwo = maxIndex;

        do {
            indexOne = indexTwo;
            System.out.print(arr[indexOne] + " ");
            indexTwo = actualSequence[indexOne];
        } while (indexOne != indexTwo);
        System.out.println();

        return max;
    }

    public static void printLISOfSpecificLength(int[] arr, int k) {
        int[] LIS = new int[arr.length];
        int[] actualSequence = new int[arr.length];

        // Initially every index has LIS = 1
        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
            actualSequence[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                    actualSequence[i] = j;
                }
            }
        }

        for (int i = LIS.length - 1; i >= 0; i--) {
            if (LIS[i] >= k) {
                int indexOne;
                int indexTwo = i;

                do {
                    indexOne = indexTwo;
                    System.out.print(arr[indexOne] + " ");
                    indexTwo = actualSequence[indexOne];
                } while (indexOne != indexTwo);

                System.out.println();
            }
        }
    }
}
