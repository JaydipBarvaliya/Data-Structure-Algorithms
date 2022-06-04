package com.leetcode.year_2020.graph.bfs.nearest_path;

import com.util.LogUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/find-shortest-distance-guard-bank/
 * Given 2d matrix
 * [0, 0 -1, -2]
 * [0, 0, 0, 0]
 * <p>
 * 0 - Room
 * -2 - Gate
 * -1 - Walls
 * So finally modified, matrix will be
 * <p>
 * [5, 4, -1, -2]
 * [4, 3,  2,  1]
 */
public class UpdateDistanceOfRoomsFromGates {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, -1, -2},
                {0, 0, 0, 0}
        };
        calculateDistance(grid);
        LogUtil.printMultiDimensionArray(grid);

        grid = new int[][]{
                {0, 0, -1, -2},
                {-2, 0, 0, 0}
        };
        calculateDistance(grid);
        LogUtil.printMultiDimensionArray(grid);

        grid = new int[][]{
                {0, 0, 0, 0, -2},
                {0, -1, -1, 0, 0},
                {0, 0, 0, -1, 0},
                {-2, -1, -1, -1, 0},
                {0, 0, 0, 0, -2}
        };
        calculateDistance(grid);
        LogUtil.printMultiDimensionArray(grid);
    }

    static class Pair {
        int x;
        int y;
        int distance;

        public Pair(final int x, final int y, final int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void calculateDistance(int[][] grid) {
        final Queue<Pair> queue = new LinkedList<>();

        // Find Gate (-2)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == -2) {
                    queue.add(new Pair(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair oldLocation = queue.remove();

            for (int[] dir : dirs) {
                int newX = oldLocation.x + dir[0];
                int newY = oldLocation.y + dir[1];
                if (!isSafe(newX, newY, grid)) {
                    grid[newX][newY] = oldLocation.distance + 1;
                    queue.add(new Pair(newX, newY, oldLocation.distance + 1));
                }
            }
        }
    }

    private static boolean isSafe(int x, int y, int[][] grid) {
        return x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 0;
    }
}
