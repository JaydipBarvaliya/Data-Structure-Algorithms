package com.leetcode.year_2020;

/**
 * @author neeraj on 15/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IslandPerimeter {

    private static int[] rows = {0, -1, 0, 1};
    private static int[] cols = {-1, 0, 1, 0};

    public static void main(String[] args) {
        int grid[][] = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += getBoundaryCount(i, j, grid);
                }
            }
        }
        return perimeter;
    }

    private static int getBoundaryCount(int i, int j, int[][] grid) {

        int row = 0;
        int col = 0;
        int perimeter = 0;
        for (int k = 0; k < 4; k++) { // Since we are allowed to move in 4 directions.
            row = i + rows[k];
            col = j + cols[k];

            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
                perimeter += 1;
            }
        }
        return perimeter;
    }
}
