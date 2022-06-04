package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * @author neeraj on 05/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicateExpressions")
public class MinimumFallingPathSum {

    public static void main(String[] args) {
        System.out.println(minFallingPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));

        System.out.println(minFallingPathSum(new int[][]{
                {1, 2, 3},
                {6, 5, 4},
                {7, 8, 9}
        }));

        System.out.println(minFallingPathSum(new int[][]{
                {3, 2, 1},
                {10, 12, 32},
                {9, 8, 7}
        }));
    }

    public static int minFallingPathSum(int[][] A) {
        /**
         * So we can choose a column in next row not farther than 1 distance away.
         * So if you are choosing
         *              [0,0] in Row 1
         *              you can only choose[1,0][1,1] in Row2.
         *
         *              or if you are choosing [0,1] in Row 1
         *              then you can choose from [1,0][1,1][1,2] in Row 2;
         *
         * As only these columns are at-most 1 distance away.
         *
         * I/p :
         *
         * [1,2,3],
         * [4,5,6],
         * [7,8,9]
         *
         * So we will take our last row as reference, as we know what minimum cost the last
         * row can provide if this is the only row we had.
         */
        int[] sum = new int[A[A.length - 1].length]; // Taking the last row.
        sum = Arrays.copyOfRange(A[A.length - 1], 0, sum.length);

        // Now we will start from 2nd last row.
        int n = A.length;
        for (int row = n - 2; row >= 0; row--) {

            // Now we will iterate through all items in this row.
            int[] sum_temp = new int[sum.length];
            for (int col = 0; col < A[row].length; col++) {
                // Now we can take upto a max of 1 distance away.
                // so we have to handle special cases when we are on 0th or last column
                if (col == 0) {
                    // this can only explore
                    // col, col+1
                    sum_temp[col] = A[row][col] + Math.min(sum[col], sum[col + 1]);
                } else if (col == A[row].length - 1) {
                    // this can only explore
                    // col-1, col
                    sum_temp[col] = A[row][col] + Math.min(sum[col - 1], sum[col]);
                } else { // All other columns which can explore
                    // col-1, col, col+1
                    sum_temp[col] = A[row][col] +
                            Math.min(sum[col - 1], Math.min(sum[col], sum[col + 1]));
                }
            }
            sum = sum_temp;
        }
        int MIN = Integer.MAX_VALUE;
        for (int i : sum) {
            MIN = Math.min(MIN, i);
        }
        return MIN;
    }

}
