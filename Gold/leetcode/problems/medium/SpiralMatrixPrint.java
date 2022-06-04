package com.leetcode.problems.medium;

/**
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SpiralMatrixPrint {

    public static void main(String[] args) {
        int a[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        spiralMatrix(a);
    }

    private static void spiralMatrix(int[][] a) {
        int m = a.length; // Total Rows
        int n = a[0].length; // Total Col
        int k = 0; // Rows Counter
        int l = 0; // Col Counter

        // If there are any rows or columns to be traversed
        while (k < m && l < n) {

            // Print 1st Row
            for (int i = l; i < n; i++) {
                System.out.print(a[k][i] + " ,");
            }
            // Since 1st Row is traverse, so reduce our space
            // Increment the row
            k++;

            // Print Last Column
            for (int j = k; j < m; j++) {
                System.out.print(a[j][n - 1] + " ,");
            }
            n--; // Last row is traversed to decrement the count

            if (m > k) { // Still some rows are to be traversed
                // So traverse the last row
                for (int i = n - 1; i >= l; i--) {
                    System.out.print(a[m - 1][i] + " ,");
                }
                m--;
            }

            if (n > l) { // Some cols are still left to be traversed
                for (int i = m - 1; i >= k; i--) {
                    System.out.print(a[i][l] + " ,");
                }
                l++;
            }
        }
    }


}
