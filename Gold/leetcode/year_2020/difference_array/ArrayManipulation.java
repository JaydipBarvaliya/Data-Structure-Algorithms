package com.leetcode.year_2020.difference_array;

/**
 * https://www.hackerrank.com/challenges/crush/problem
 * <p>
 * Array Manipulation
 *
 * @author neeraj on 20/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ArrayManipulation {

    public static void main(String[] args) {
        System.out.println(arrayManipulation(10, new int[][]{
                {2, 6, 8},
                {3, 5, 7},
                {1, 8, 1},
                {5, 9, 15}
        }));

    }


    // Complete the arrayManipulation function below.
    public static long arrayManipulation(int n, int[][] queries) {
        int[] manipulatedArray = new int[n + 1];

        for (int[] query : queries) {
            int a = query[0]; // Start index
            int b = query[1]; // End index
            int k = query[2]; // What to update

            // we will add "k" to Manipulated Array[a]
            // and subtract "k" from Manipulated Array[b+1] position
            // to negate that addition effect after bth position
            manipulatedArray[a] += k;
            manipulatedArray[b+1] -= k;
        }

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i : manipulatedArray) {
            sum += i;
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
