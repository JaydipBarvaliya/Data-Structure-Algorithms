package com.company.nearbuy;

/**
 * Calculate the Maximum Difference b/w the values at i and the values left to it
 * <p>
 * arr[i] represent the maximum difference b/w the val of arr[i] and the minimum value in the left
 */
public class MaximumDifferenceInAnArray {

    public static void main(String[] args) {
        System.out.println(maxDifference(new int[]{2, 3, 10, 2, 4, 8, 1}));
    }

    // Complete the maxDifference function below.
    public static int maxDifference(int[] a) {

        int MIN_VAL = a[0];
        int MAX_DIFF = -1;
        int diff = 0;

        for (int i = 1; i < a.length; i++) {
            if (a[i] > MIN_VAL) {
                diff = a[i] - MIN_VAL;
                if (diff > MAX_DIFF) {
                    MAX_DIFF = diff;
                }
            } else {
                MIN_VAL = a[i];
            }
        }
        return MAX_DIFF;
    }

}
