package com.leetcode.problems.medium;

/**
 * https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 *
 * @author neeraj on 05/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxIncreaseToKeepCitySkyline {

    public static void main(String[] args) {
        maxIncreaseKeepingSkyline(new int[][]{
                {3, 0, 8, 4},
                {2, 4, 5, 7},
                {9, 2, 6, 3},
                {0, 3, 1, 0}
        });
    }

    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid[0].length];
        // Calculate the maximum in Row and column.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                colMax[j] = Math.max(colMax[j], grid[i][j]);
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
            }
        }

        // Now let's traverse it and find actual solution
        int maxIncrease = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                maxIncrease += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return maxIncrease;
    }
}
