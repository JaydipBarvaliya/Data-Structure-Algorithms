package com.geeksforgeeks.array.sub_array_problem;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MaximumProductSubArray {

    public static void main(String[] args) {
        maximumProductSubArray(new int[]{6, -3, -10, 0, 2});
        maximumProductSubArray(new int[]{-1, -3, -10, 0, 60});
        maximumProductSubArray(new int[]{-1, -7, -10, 0, 60});
        maximumProductSubArray(new int[]{-2, -3, 0, -2, -40});
        maximumProductSubArray(new int[]{1, -2, -3, 0, 7, -8, -2});
    }

    public static void maximumProductSubArray(int[] arr) {
        int MAX_ENDING_HERE = arr[0];
        int MIN_ENDING_HERE = arr[0];
        int MAX_TILL_NOW = 1; // Assuming there is 1 subarray with atleast 1 as the product

        for (int i = 1; i < arr.length; i++) {
            int max = MAX_ENDING_HERE;
            int min = MIN_ENDING_HERE;

            MAX_ENDING_HERE = max(arr[i] * max, max(arr[i] * min, arr[i]));
            MIN_ENDING_HERE = min(arr[i] * max, min(arr[i] * min, arr[i]));

            if (MAX_TILL_NOW < MAX_ENDING_HERE) {
                MAX_TILL_NOW = MAX_ENDING_HERE;
            }
        }
        System.out.println(MAX_TILL_NOW);
    }
}
