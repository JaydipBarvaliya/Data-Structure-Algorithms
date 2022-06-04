package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.ArrayRotation;

import java.util.Arrays;

public class MaximumSumInLongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(getMaximumSum(new int[]{1, 101, 2, 3, 100, 4, 5}));
        System.out.println(getMaximumSum(new int[]{3, 4, 5, 10}));
    }

    public static int getMaximumSum(int[] input) {
        int[] sumMatrix = new int[input.length];
        sumMatrix[0] = input[0];
        int i = 0;
        for (int j = 1; j < input.length; ) {
            if (input[j] > input[i]) {
                if (sumMatrix[j] < sumMatrix[i] + input[j]) {
                    sumMatrix[j] = sumMatrix[i] + input[j];
                } else {
                    sumMatrix[j] = input[j];
                }
            }
            i++;
            if (i == j) {
                j++;
                i = 0;
            }
        }
        ArrayRotation.printArray(sumMatrix);
        Arrays.sort(sumMatrix);
        return sumMatrix[input.length - 1];
    }
}
