package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;

public class ProductArrayPuzzle {

    public static void main(String[] args) {
        productArrayPuzzle(new int[]{10, 3, 5, 6, 2});
    }

    public static void productArrayPuzzle(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] product = new int[n];
        left[0] = 1; // 1st element of left array will be 1 always bcoz on array index 1 we want the sum of array till index 0;
        right[n - 1] = 1; // Same reason as above but for the sum of right side element excluding the current element

        // Left side sum we start with 1 as on 0th index left side sum is 1 as we assumed
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * arr[i - 1];
        }

        // Right Side sum
        for (int j = n - 2; j >= 0; j--) {
            right[j] = right[j + 1] * arr[j + 1];
        }

        // Finally Product Array
        for (int k = 0; k < n; k++) {
            product[k] = left[k] * right[k];
        }

        ArrayRotation.printArray(product);
    }
}
