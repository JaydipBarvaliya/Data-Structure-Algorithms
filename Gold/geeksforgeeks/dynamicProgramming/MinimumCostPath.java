package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class MinimumCostPath {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}
        };

        System.out.println("Minimum Cost to reach from (0,0) to (2,2) "+getMinimumCostPath(arr, 3, 3));


        int [][]arr1 = {
                {1, 3, 5, 8},
                {4, 2, 1, 7},
                {4, 3, 2, 3}
        };
        System.out.println("Minimum Cost to reach from (0,0) to (3,4) "+getMinimumCostPath(arr1, 3, 4));

    }

    public static int getMinimumCostPath(int[][] arr, int m, int n) {
        int[][] cost = new int[m][n];

        cost[0][0] = arr[0][0];

        for (int i = 1; i < n; i++)
            cost[0][i] = cost[0][i - 1] + arr[0][i];

        for (int i = 1; i < m; i++)
            cost[i][0] = cost[i - 1][0] + arr[i][0];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cost[i][j] = MinimumEditDistance.minimum(cost[i-1][j-1], cost[i][j-1], cost[i-1][j]) + arr[i][j];
            }
        }
        Rotate2DMatrix.print2DArray(cost);
        return cost[m-1][n-1];
    }
}
