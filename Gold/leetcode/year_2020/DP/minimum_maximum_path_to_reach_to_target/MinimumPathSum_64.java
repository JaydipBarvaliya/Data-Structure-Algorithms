package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

/**
 * @author neeraj on 15/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumPathSum_64 {

    public static void main(String[] args) {
        minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        minPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        minPathSum(new int[][]{
                {5, 0, 1, 1, 2, 1, 0, 1, 3, 6, 3, 0, 7, 3, 3, 3, 1},
                {1, 4, 1, 8, 5, 5, 5, 6, 8, 7, 0, 4, 3, 9, 9, 6, 0},
                {2, 8, 3, 3, 1, 6, 1, 4, 9, 0, 9, 2, 3, 3, 3, 8, 4},
                {3, 5, 1, 9, 3, 0, 8, 3, 4, 3, 4, 6, 9, 6, 8, 9, 9},
                {3, 0, 7, 4, 6, 6, 4, 6, 8, 8, 9, 3, 8, 3, 9, 3, 4},
                {8, 8, 6, 8, 3, 3, 1, 7, 9, 3, 3, 9, 2, 4, 3, 5, 1},
                {7, 1, 0, 4, 7, 8, 4, 6, 4, 2, 1, 3, 7, 8, 3, 5, 4},
                {3, 0, 9, 6, 7, 8, 9, 2, 0, 4, 6, 3, 9, 7, 2, 0, 7},
                {8, 0, 8, 2, 6, 4, 4, 0, 9, 3, 8, 4, 0, 4, 7, 0, 4},
                {3, 7, 4, 5, 9, 4, 9, 7, 9, 8, 7, 4, 0, 4, 2, 0, 4},
                {5, 9, 0, 1, 9, 1, 5, 9, 5, 5, 3, 4, 6, 9, 8, 5, 6},
                {5, 7, 2, 4, 4, 4, 2, 1, 8, 4, 8, 0, 5, 4, 7, 4, 7},
                {9, 5, 8, 6, 4, 4, 3, 9, 8, 1, 1, 8, 7, 7, 3, 6, 9},
                {7, 2, 3, 1, 6, 3, 6, 6, 6, 3, 2, 3, 9, 9, 4, 4, 8}
        });

        minPathSumUsingDP(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        minPathSumUsingDP(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        minPathSumUsingDP(new int[][]{
                {5, 0, 1, 1, 2, 1, 0, 1, 3, 6, 3, 0, 7, 3, 3, 3, 1},
                {1, 4, 1, 8, 5, 5, 5, 6, 8, 7, 0, 4, 3, 9, 9, 6, 0},
                {2, 8, 3, 3, 1, 6, 1, 4, 9, 0, 9, 2, 3, 3, 3, 8, 4},
                {3, 5, 1, 9, 3, 0, 8, 3, 4, 3, 4, 6, 9, 6, 8, 9, 9},
                {3, 0, 7, 4, 6, 6, 4, 6, 8, 8, 9, 3, 8, 3, 9, 3, 4},
                {8, 8, 6, 8, 3, 3, 1, 7, 9, 3, 3, 9, 2, 4, 3, 5, 1},
                {7, 1, 0, 4, 7, 8, 4, 6, 4, 2, 1, 3, 7, 8, 3, 5, 4},
                {3, 0, 9, 6, 7, 8, 9, 2, 0, 4, 6, 3, 9, 7, 2, 0, 7},
                {8, 0, 8, 2, 6, 4, 4, 0, 9, 3, 8, 4, 0, 4, 7, 0, 4},
                {3, 7, 4, 5, 9, 4, 9, 7, 9, 8, 7, 4, 0, 4, 2, 0, 4},
                {5, 9, 0, 1, 9, 1, 5, 9, 5, 5, 3, 4, 6, 9, 8, 5, 6},
                {5, 7, 2, 4, 4, 4, 2, 1, 8, 4, 8, 0, 5, 4, 7, 4, 7},
                {9, 5, 8, 6, 4, 4, 3, 9, 8, 1, 1, 8, 7, 7, 3, 6, 9},
                {7, 2, 3, 1, 6, 3, 6, 6, 6, 3, 2, 3, 9, 9, 4, 4, 8}
        });
    }

    /**
     * This is a DP solution
     * Since we can move in 2 directions, RIGHT and BOTTOM
     * So we will keep track of which will be cheaper/less costly
     */
    public static int minPathSumUsingDP(int[][] grid) {
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

    static int minimumDistance = Integer.MAX_VALUE;

    public static int minPathSum(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        minimumDistance = Integer.MAX_VALUE;
        doDFS(grid, 0, 0, grid[0][0], visited);
        System.out.println(minimumDistance);
        return minimumDistance;
    }

    private static void doDFS(int[][] grid, int row, int col, int distanceFromOrigin, boolean[][] visited) {
        visited[row][col] = true;
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minimumDistance = Math.min(distanceFromOrigin, minimumDistance);
        }

        if (isSafe(grid, row, col + 1, visited)) {
            doDFS(grid, row, col + 1, distanceFromOrigin + grid[row][col + 1], visited);
        }
        if (isSafe(grid, row + 1, col, visited)) {
            doDFS(grid, row + 1, col, distanceFromOrigin + grid[row + 1][col], visited);
        }

        visited[row][col] = false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && visited[row][col] == false;
    }

}
