package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/triangle/
 *
 * @author neeraj on 05/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumWeightPathToTriangle {

    public static void main(String[] args) {
        LogUtil.logIt("Minimum total is " + solve(new int[][]{
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
                /**
                 * Assume the input as triangle
                 *
                 * [
                 *      [2],
                 *     [3,4],
                 *    [6,5,7],
                 *   [4,1,8,3]
                 * ]
                */
        }));
    }

    private static int solve(int[][] input) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int row[] : input) {
            triangle.add(Arrays.stream(row).boxed().collect(Collectors.toList()));
        }
        return minimumTotalOtherWay(triangle);
    }

    static int[][] T; // memorization

    /**
     * Here we are doing bottom-up approach..
     * we are starting from 1st row and going all the way to the last.
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        /**
         * So can be easily solved using recursive approach
         * and later we will memorize the solution.
         *
         * What are the changing parameters we have here.....
         * Row and Column..
         *
         * we will start from 1st row and go till all the way to bottom...
         * to find solution for sub-problem and bubble up to top.
         */
        T = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int[] row : T) {
            Arrays.fill(row, -1);
        }
        return findMinimumTotal(triangle, 0, 0);
    }

    private static int findMinimumTotal(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

        // Return from cache
        if (T[row][col] != -1) return T[row][col];

        int valueAtCurrentRow = triangle.get(row).get(col);
        // so now we need to know which diagonal below item can
        // yield the best possible result.
        // So we will try and check min from both adjacent row...left and right diagonal.
        // Now since we are staring from 1st row so in the left diagonal we can't grow much
        // hence we don't modify the column.
        int minimumFromDiagonallyBelowRow = Math.min(findMinimumTotal(triangle, row + 1, col),
                findMinimumTotal(triangle, row + 1, col + 1));

        return T[row][col] = valueAtCurrentRow + minimumFromDiagonallyBelowRow;

    }

    public static int minimumTotalOtherWay(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];

        int counter = 0; // Initializing the dp with last row.
        for (int item : triangle.get(triangle.size() - 1)) {
            dp[counter++] = item;
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + row.get(j);
            }
        }
        return dp[0];
    }
}
