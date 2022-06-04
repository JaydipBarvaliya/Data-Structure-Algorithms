package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/longest-possible-route-in-a-matrix-with-hurdles/
 *
 * @author neeraj on 12/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestPossibleRouteInMatrix {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        getMaxPath(grid, new int[]{0, 0}, new int[]{1, 7});
    }

    static int maxPath = Integer.MIN_VALUE;

    public static int getMaxPath(int[][] grid, int[] source, int[] destination) {
        maxPath = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        traverse(grid, source[0], source[1], destination, 0, visited, new ArrayList<>());
        LogUtil.logIt("Maximum Path from Source to Destination....." + maxPath);
        System.out.println(maxPathRoute);
        return maxPath;
    }

    //
    static int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static List<String> maxPathRoute = new ArrayList<>();

    private static void traverse(int[][] grid, int row, int col,
                                 int[] destination, int distance, boolean[][] visited,
                                 List<String> path) {

        // Safe Guard
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col] || grid[row][col] == 0) {
            return;
        }

        if (row == destination[0] && col == destination[1]) {
            maxPath = Math.max(maxPath, distance);
            if (maxPathRoute.size() < path.size()) {
                path.add(row + "," + col);
                maxPathRoute = new ArrayList<>(path);
            }
            return;
        }

        visited[row][col] = true;


        path.add(row + "," + col);

        // Forward
        traverse(grid, row, col + 1, destination, distance + 1, visited, path);

        // Down
        traverse(grid, row + 1, col, destination, distance + 1, visited, path);

        // Left
        traverse(grid, row, col - 1, destination, distance + 1, visited, path);

        // Up
        traverse(grid, row - 1, col, destination, distance + 1, visited, path);

        path.remove(path.size() - 1);
        visited[row][col] = false;
    }
}
