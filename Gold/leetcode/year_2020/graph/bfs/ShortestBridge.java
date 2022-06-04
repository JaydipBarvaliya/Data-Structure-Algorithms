package com.leetcode.year_2020.graph.bfs;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * https://leetcode.com/problems/shortest-bridge/
 * 934. Shortest Bridge
 */
public class ShortestBridge {

    public static void main(String[] args) {
        System.out.println(shortestBridge(new int[][]{
                {1, 0},
                {0, 1}
        }));

        System.out.println(shortestBridge(new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));

        System.out.println(shortestBridge(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        }));

        System.out.println(shortestBridge(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0}
        }));

    }

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int shortestBridge(int[][] grid) {
        // Since it's guaranteed to have at-least 1 answer
        // so let's skip the grid null and empty check
        // and we know for sure to find min distance between 2 islands(i.e 1) we need
        // to do BFS
        boolean[][] visited = new boolean[grid.length][grid.length];
        Queue<int[]> queue = new LinkedList<>();
        boolean foundIsland = false;
        for (int i = 0; i < grid.length && !foundIsland; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    dfsAndColor(i, j, grid, visited, 2);
                    foundIsland = true;
                    break;
                }
            }
        }


        // Now problem is simple find nearest path from 2 to 1
        int[][] distance = new int[grid.length][grid.length];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    distance[i][j] = 0;
                }
            }
        }

//        LogUtil.printMultiDimensionArray(grid);
//        System.out.println("------------------------------------");
//        LogUtil.printMultiDimensionArray(distance);

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int currDistance = distance[coordinates[0]][coordinates[1]];
            for (int[] dir : directions) {
                int[] nextDirection = new int[]{dir[0] + coordinates[0], dir[1] + coordinates[1]};
                if (isSafe(grid, nextDirection) && distance[nextDirection[0]][nextDirection[1]] == -1) {
                    distance[nextDirection[0]][nextDirection[1]] = currDistance + 1;
                    queue.add(nextDirection);
                }
            }
        }

//        System.out.println("------------------------------------");
//        LogUtil.printMultiDimensionArray(distance);
        int MIN_FLIPS = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if (grid[i][j] == 1 && distance[i][j] != -1) {
                    MIN_FLIPS = Math.min(MIN_FLIPS, distance[i][j]);
                }
            }
        }
        return MIN_FLIPS - 1;
    }

    private static boolean isSafe(final int[][] grid, final int[] nextDirection) {
        int x = nextDirection[0], y = nextDirection[1];
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public static void dfsAndColor(int row, int col, int[][] grid, boolean[][] visited, int color) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] != 1) {
            return;
        }
        grid[row][col] = color;
        visited[row][col] = true;
        for (int[] direction : directions) {
            dfsAndColor(row + direction[0], col + direction[1], grid, visited, color);
        }
    }
}
