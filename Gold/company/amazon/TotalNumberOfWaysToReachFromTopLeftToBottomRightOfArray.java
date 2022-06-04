package com.company.amazon;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * Problem 157: (prerequisite) Amazon Interview Questions
 */
public class TotalNumberOfWaysToReachFromTopLeftToBottomRightOfArray {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("Total Number of Ways " + getTotalNumberOfWays(matrix));
    }

    /**
     * _              _
     * |1    2   3   4 |
     * |5    6   7   8 |
     * |9    10  11  12|
     * |13   14  15  16|
     * _               _
     * <p>
     * We are allowed to Move only bottom or right
     *
     * @param arr
     * @return
     */
    public static int getTotalNumberOfWays(int[][] arr) {
        int[][] noOfWays = new int[arr.length][arr[0].length];

        // Since we know we can only move right or bottom
        // So In order to reach all values of 1st row there is only one way that is to move right
        // Similarly for 1st column there is only 1 way to move from 1st column top to bottom.
        for (int i = 0; i < noOfWays[0].length; i++) // 1st Row
            noOfWays[0][i] = 1;
        for (int i = 0; i < noOfWays.length; i++) // 1st Column
            noOfWays[i][0] = 1;

        // Position [0][0] is our starting point so that will always be 1

        // Now actual problem starts
        for (int i = 1; i < noOfWays.length; i++) {
            for (int j = 1; j < noOfWays[i].length; j++) {
                noOfWays[i][j] = noOfWays[i][j - 1] + noOfWays[i - 1][j];
            }
        }
        Rotate2DMatrix.print2DArray(noOfWays);
        return noOfWays[noOfWays.length - 1][noOfWays[0].length - 1];
    }
}
