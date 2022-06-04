package com.leetcode.year_2020.array;

import java.util.TreeSet;

public class MaximumSumTriplet {

    public static void main(String[] args) {
        System.out.println(solve(new int[]{18468, 6335, 26501, 19170, 15725, 11479, 29359, 26963, 24465, 5706, 28146, 23282, 16828, 9962, 492, 2996, 11943, 4828, 5437, 32392, 14605}));
        System.out.println(solve(new int[]{3603, 24351, 10292, 30837, 9375, 11021, 4597, 24022, 27349, 23200, 19669, 24485, 8282, 4735, 54, 2000, 26419, 27939, 6901, 3789, 18128, 468, 3729, 14894, 24649, 22484, 17808, 2422, 14311, 6618, 22814}));
    }

    public static int solve(int[] A) {
        int[] rightMax = new int[A.length];
        rightMax[rightMax.length - 1] = A[A.length - 1];

        for (int i = A.length - 2; i >= 0; i--) {
            rightMax[i] = rightMax[i+1] > A[i] ? rightMax[i+1] : A[i];
        }

        // We can't take left max since in right side it's guaranteed Aj < Ak, so we can take any greater element in right
        // but in left there is no such guaranteed, we might get a number greater than A[j] in left side and we might need a smaller than that

        // Now we will try for j at every  element from 1 to N-1
        // Why ? {SOME MAX IN LEFT} + jth Element + {SOME MAX IN RIGHT} will give you right

        // So lets store left side in sorted order
        TreeSet<Integer> lowerValues = new TreeSet<>();
        lowerValues.add(Integer.MIN_VALUE); // For comparison

        int MAX_SUM = Integer.MIN_VALUE;
        for (int j = 0; j < A.length-1; j++) {
            if(rightMax[j+1] > A[j]) {
                MAX_SUM = Math.max(MAX_SUM, lowerValues.lower(A[j]) + A[j] + rightMax[j+1]);
                lowerValues.add(A[j]);
            }
        }
        return MAX_SUM;
    }
}
