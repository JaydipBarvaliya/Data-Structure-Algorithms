package com.leetcode.year_2020;

/**
 * @author neeraj on 18/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class MinPathSum {

    static int minimumDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minPathSum(new int[][]{{1, 2}, {1, 1}}));
        System.out.println(minPathSum(new int[][]{{1, 2, 5}, {3, 2, 1}}));
    }

    /**
     * This is a DP solution
     * Since we can move in 2 directions, RIGHT and BOTTOM
     * So we will keep track of which will be cheaper/less costly
     */
    public static int minPathSum(int[][] grid) {
        int[][] pathSum = new int[grid.length][grid[0].length];

        // So for Grid with

        /**
         * [
         *   [1,3,1],
         *   [1,5,1],
         *   [4,2,1]
         * ]
         *
         * Path Sum for 1st Row and Col look like
         * [
         *  [1, 4, 5],
         *  [2, X, X],
         *  [6, X, X]
         * ]
         *
         * X will be calculated during this problem solving.
         */

        pathSum[0][0] = grid[0][0];

        // For 1st Row there is just one way to reach that is start from top left and
        // Move right 1 step.
        for (int col = 1; col < grid[0].length; col++) {
            pathSum[0][col] = pathSum[0][col - 1] + grid[0][col];
        }

        // For 1st Col there is just one way to reach that is start from top left and
        // Move down 1 step.
        for (int row = 1; row < grid.length; row++) {
            pathSum[row][0] = pathSum[row - 1][0] + grid[row][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                pathSum[i][j] = Math.min(pathSum[i - 1][j], pathSum[i][j - 1]) + grid[i][j];
            }
        }
        return pathSum[pathSum.length - 1][pathSum[0].length - 1];
    }

    /**
     * DFS will give you TLE for a longer GRID.
     */
    public static int minPathSumWithDFS(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        minimumDistance = Integer.MAX_VALUE;
        doDFS(0, 0, grid, grid[0][0], visited);
        return minimumDistance;
    }

    private static void doDFS(int row, int col, int[][] grid, int currentSum, boolean[][] visited) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minimumDistance = Math.min(currentSum, minimumDistance);
            return;
        }

        // Go Down
        if (isSafe(row + 1, col, grid, visited)) {
            doDFS(row + 1, col, grid, currentSum + grid[row + 1][col], visited);
        }

        // Go Right
        if (isSafe(row, col + 1, grid, visited)) {
            doDFS(row, col + 1, grid, currentSum + grid[row][col + 1], visited);
        }

        //UnSelect the chosen path
        visited[row][col] = false;
    }

    private static boolean isSafe(int row, int col, int[][] grid, boolean[][] visited) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                && !visited[row][col];
    }
}
