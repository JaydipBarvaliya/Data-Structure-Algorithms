package com.leetcode.year_2020.binary_search;

import com.geeksforgeeks.array.Rotate2DMatrix;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * @author neeraj on 16/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Search2DMatrix_First {

    public static void main(String[] args) {
        searchMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3);

        System.out.println(searchMatrixSimple(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3));
    }

    public static boolean searchMatrixSimple(int[][] matrix, int target) {
        /**
         * {@link Search2DMatrix_II} way of solving problem.
         ** Simply we can start from bottom left corner
         ** and perform simple operation
         **/
        int row = matrix.length - 1;
        int col = 0;

        while (col < matrix[0].length && row >= 0) {
            if (matrix[row][col] == target) return true;
            if (matrix[row][col] > target) row--;
            else col++;
        }
        return false;
    }

    /**
     * We can apply Binary search since the matrix given to us is
     * 1) Sorted with rows
     * 2) Row continuity is there. (The first integer of each row is greater than the last integer of the previous row.)
     * <p>
     * So based on the index of 1D version of 2D Matrix, we can find out which row and column we are in like :
     * <p>
     * Row = index / totalColumns
     * Col = index % totalColumns.
     * <p>
     * Reason why:
     * <p>
     * ROWS(why /):
     * The row in which an item sits in is depend on the number of "items per row", because the less item we can put in a row
     * it will increase the number of rows in the total array.
     * <p>
     * COLS(why Mod): For wrap around in array, we always use Mod, remember when we have to move pointers in array%size, move it back in the array.
     * <p>
     * We are iterating 2D matrix as 1D Matrix only, So assume matrix elements are like this :
     * <p>
     * 0    1    2   3
     * 4    5    6   7
     * 8    9    10  11
     * <p>
     * Now we start from low = 0, and high = 11
     * , Let's find out mid = 0 + (11 - 0) / 2 = 5;
     * So how to find the value at position 5 of a 2D Matrix
     * is by making row = (mid / totalColumns) = 5 / 4  => 1;
     * col = mid % totalColumns = 5 % 4 => 1
     * So [1,1] is the value of mid.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        boolean result = binarySearch(matrix, 0, matrix.length * matrix[0].length - 1, target);
        LogUtil.logIt("Searching " + target + " in matrix");
        Rotate2DMatrix.print2DArray(matrix);
        LogUtil.logIt(result ? "Found in Matrix" : "Not Found......!!");
        return result;
    }

    private static boolean binarySearch(int[][] matrix, int low, int high, int target) {
        if (low <= high) {
            // Similar to formula of calculating the row and column.
            int mid = low + (high - low) / 2;
            int valueAtMid = matrix[mid / matrix[0].length][mid % matrix[0].length];

            if (valueAtMid == target) {
                return true;
            }
            if (valueAtMid < target) {
                return binarySearch(matrix, mid + 1, high, target);
            } else {
                return binarySearch(matrix, low, mid - 1, target);
            }
        }
        return false; // low overshoot high
    }
}
