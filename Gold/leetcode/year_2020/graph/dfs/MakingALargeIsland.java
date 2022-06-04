package com.leetcode.year_2020.graph.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/making-a-large-island/
 * 827. Making A Large Island
 */
public class MakingALargeIsland {

    public static void main(String[] args) {
//        System.out.println(largestIsland(new int[][]{{1, 0}, {0, 1}}));
//        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 0}}));
//        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 1}}));
        System.out.println(largestIsland(new int[][]{{1, 1}, {0, 0}}));
    }

    public static int largestIsland(int[][] grid) {
        /**
         * We can do DFS from all ones and color them with certain color code,
         *
         * Assume we have 4 island, we will paint all connected cells of 1st Island with Color 2
         * and with Color 3,4,5 and so on for further connected cells of different island
         *
         * Refer image for better understanding.
         * https://s3-lc-upload.s3.amazonaws.com/users/votrubac/image_1525310120.png
         *
         * Once you have this Now if the island are just 1 cell(basically 0 cell) away, then that can be inverted
         * into 1 and make a big island.
         *
         * So call paint(row, col, paintColor) for all 1's in the grid and while doing this make sure to count the number
         * of elements for each painted color
         *
         * Map<PaintColor, TotalNumberOfCellsPaintedWithColor>
         *          2               5
         *          3               4
         *
         * Now iterate matrix find out all zeros, and for each zero check in 4 direction if you find any painted cell
         * add 1 to that island(basically count the number of cells on that island) since we can convert this zero.
         * and similarly if there is any other island which can happily connect with the other island
         */
        final Map<Integer, Integer> paintToCells = new HashMap<>();
        paintToCells.put(0, 0); //We won't paint island 0, hence make its size 0, we will use this value later
        int paintColor = 2; // Since 0 and 1 are already present in cells, hence we are starting to use paint color.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    paintToCells.put(paintColor, paint(grid, i, j, paintColor++));
                }
            }
        }

        Integer result = paintToCells.getOrDefault(2, 0); // Default to first island we find
        // Now you should simply find 0 in the grid and check all 4 directions
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    // Using Set to avoid colors of same island, being populated, we want to see how many island
                    // are connected to this 0.
                    final Set<Integer> set = new HashSet<>();
                    set.add(i > 0 ? grid[i - 1][j] : 0); // UP
                    set.add(j < grid[0].length - 1 ? grid[i][j + 1] : 0); // Right
                    set.add(i < grid.length - 1 ? grid[i + 1][j] : 0); // Bottom
                    set.add(j > 0 ? grid[i][j - 1] : 0); // Left

                    int newSize = 1; // Since zero will be converted to 1
                    for (int color : set) {
                        newSize += paintToCells.get(color);
                    }
                    result = Math.max(result, newSize);
                }
            }
        }
        return result;

    }

    public static int paint(int[][] grid, int row, int col, int paintColor) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1) return 0;
        grid[row][col] = paintColor;
        return 1 +
                paint(grid, row - 1, col, paintColor) + // Top Row
                paint(grid, row, col + 1, paintColor) + // Right Column
                paint(grid, row + 1, col, paintColor) + // Bottom row
                paint(grid, row, col - 1, paintColor); // Left Column
    }
}
