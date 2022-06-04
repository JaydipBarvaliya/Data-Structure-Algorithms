package com.leetcode.year_2020.binary_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 15/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Search2DMatrix_II {

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 26));

        System.out.println(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15}
        }, 11));
    }

    /**
     * In this solution also we are going to eliminate the rows and columns
     * but instead we will do it in one pass as explained by genius.
     * https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m%2Bn)-Java-solution
     * and image
     * https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m+n)-Java-solution/68155
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrixInOptimalManner(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) // If nothing is present, always return false.
            return false;

        // We will start from top right corner
        // and move inwards and downwards
        //   <------
        //         ||
        //         ||
        //         ||
        //         \/
        int row = 0;
        int col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        // Find Rows to be rejected
        // This can be done by performing the binary search on first items of each row
        // and identifying the last row which is eligible for search

        int row = firstPositionGreaterThanTarget(matrix, 0, matrix.length - 1, true, target);

        // Similarly find cols to be rejected.

        int col = firstPositionGreaterThanTarget(matrix, 0, matrix[0].length - 1, false, target);

        row = row == -1 ? matrix.length : row;
        col = col == -1 ? matrix[0].length : col;

        for (int i = 0; i < row; i++) {
            if (binarySearch(matrix[i], 0, col - 1, target)) {
                System.out.println("Found the target....");
                return true;
            }
        }
        System.out.println("Target not found !!!!!");
        return false;
    }

    private static boolean binarySearch(int[] matrix, int low, int high, int target) {
        if (low <= high) {

            // Why mid = low + (high - low)/2
            /**
             * Mid is somewhere between low and high, so lets assume
             * mid = low + x 			// cool… this is convincing so far yet :P
             * // Hey!! I am substituting your fav mid formula down here :P
             * ( high + low ) / 2 = low + x
             *
             * low + x = ( high + low ) / 2
             * x = ( high + low ) / 2 - low
             * x = ( high + low - 2 * low ) / 2
             * x = ( high - low ) / 2
             *
             * // yeah... I got the value of “x"... jinga lala hooooo...
             */
            Map<Integer, Integer> map = new HashMap<>();
            map.hashCode();
            int mid = low + (high - low) / 2;

            if (matrix[mid] == target) {
                return true;
            }
            if (matrix[mid] > target)
                return binarySearch(matrix, low, mid - 1, target);
            else
                return binarySearch(matrix, mid + 1, high, target);
        }
        return false;
    }

    private static int firstPositionGreaterThanTarget(int[][] matrix, int low, int high, boolean isRowSearch, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (isRowSearch) { // Eliminate Rows
                if (matrix[mid][0] > target) { // Can be the first position but lets check

                    if (mid == low) { // there is no element on the left
                        return mid;
                    }
                    if (matrix[mid - 1][0] > target) {
                        return firstPositionGreaterThanTarget(matrix, low, mid - 1, true, target);
                    } else {
                        return mid;
                    }
                } else {
                    return firstPositionGreaterThanTarget(matrix, mid + 1, high, true, target);

                }
            } else { // Eliminate columns
                if (matrix[0][mid] > target) { // Can be the first position but lets check
                    if (mid == low) { // there is no element on the left
                        return mid;
                    }
                    if (matrix[0][mid - 1] > target) {
                        return firstPositionGreaterThanTarget(matrix, low, mid - 1, false, target);
                    } else {
                        return mid;
                    }
                } else {
                    return firstPositionGreaterThanTarget(matrix, mid + 1, high, false, target);
                }
            }
        }
        return -1;
    }
}
