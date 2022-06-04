package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;

/**
 * Problem : 146 Amazon Interview Question
 */
public class MaximumSumSuchThatNo2ElementsAreAdjacent {
    public static void main(String[] args) {
        findMaxSumUsingDP(new int[]{1, 4, 1, 1, 7}, 5);
        findMaxSumUsingDP(new int[]{5, 5, 10, 100, 10, 5}, 6);

    }

    // Correct Solution
    public static void findMaxSumUsingDP(int[] arr, int n) {
        if (n == 1)
            System.out.println("Find Max Sum Using DP .........:::===>" + arr[0]);
        if (n == 2)
            System.out.println("Find Max Sum Using DP .........:::===>" + Math.max(arr[0], arr[1]));

        int[] DP = new int[n];
        DP[0] = arr[0];
        DP[1] = arr[1];

        for (int i = 2; i < arr.length; i++) {
            DP[i] = Math.max((arr[i] + DP[i - 2]), DP[i - 1]);
        }
        ArrayRotation.printArray(DP);
        System.out.println("Find Max Sum Using DP .........:::===>" + DP[n - 1]);
    }
}