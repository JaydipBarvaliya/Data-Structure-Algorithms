package com.leetcode.year_2020.graph.depth_first_search_from_boundary;

/**
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofClosedIslands {

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        System.out.println(closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        }));
        System.out.println(closedIsland(new int[][]{
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        }));
        System.out.println(closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        }));
    }

    public static int closedIsland(int[][] grid) {
        /**
         * So we know if on boundary 0 is present, it will definitely not allow
         * some 0's to not make island, so eliminate these types of cells.
         * We do boundaryLevel dfs for the same
         *
         * Step 1) We will check all the boundaries first
         *          if we found any 0, we will flip it to '1' and it's related island to '1' (since 0 is land and 1 is water in question)
         * Step 2) Now this problem is just count no of island problem with 0 being considered as island.
         */

        // First Column and Last Column
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 0) {
                doDFSForBoundary(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 0) {
                doDFSForBoundary(grid, i, grid[0].length - 1);
            }
        }

        // First Row and Last Row
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == 0) {
                doDFSForBoundary(grid, 0, j);
            }
            if (grid[grid.length - 1][j] == 0) {
                doDFSForBoundary(grid, grid.length - 1, j);
            }
        }

        // Now Number of island problem with considering 0 as the land and 1 as water.
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int countOfClosedIsland = 0;
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    connectAllIsland(grid, i, j, visited);
                    countOfClosedIsland++;
                }
            }
        }
        return countOfClosedIsland;
    }

    private static void connectAllIsland(int[][] grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true;

        for (int[] direction : directions) {
            if (isSafeToMove(grid, row + direction[0], col + direction[1])
                    && !visited[row + direction[0]][col + direction[1]]) {
                connectAllIsland(grid, row + direction[0], col + direction[1], visited);
            }
        }
    }

    private static void doDFSForBoundary(int[][] grid, int row, int col) {
        grid[row][col] = 1; // Flipping to 1.
        for (int[] direction : directions) {
            if (isSafeToMove(grid, row + direction[0], col + direction[1])) {
                doDFSForBoundary(grid, row + direction[0], col + direction[1]);
            }
        }
    }

    private static boolean isSafeToMove(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length - 1
                && col >= 0 && col < grid[row].length - 1
                && grid[row][col] == 0;
    }
}
