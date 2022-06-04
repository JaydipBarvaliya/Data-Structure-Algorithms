package com.leetcode.year_2020.graph.bfs.nearest_path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 *
 * @author neeraj on 01/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RottingOranges {

    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        }));
        System.out.println(orangesRotting(new int[][]{
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        }));
        System.out.println(orangesRotting(new int[][]{
                {0, 2}
        }));
        System.out.println(orangesRotting(new int[][]{
                {0, 1}
        }));
    }

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int orangesRotting(int[][] grid) {
        /**
         * This problem asks us to give the Minimum Number of minutes
         * to Rot All other oranges and if not possible return -1.
         * So this problem is essentially asking us to find the minimum distance
         * required to reach to a fresh orange from Rotten Orange.
         *
         * Now this problem is very much similar to {@link ZeroOneMatrix} and we always solve nearest minimum problem
         * in graph using BFS
         *
         * In Every minute 1 rotten orange rot 4 sides(top, right, bottom , left) if present.
         * So we will do the BFS from every rotten orange
         */
        int[][] minutes = new int[grid.length][grid[0].length];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) { // Fresh Orange
                    minutes[i][j] = -1;
                }
                if (grid[i][j] == 2) {
                    minutes[i][j] = 0;
                    queue.add(new Pair(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair rottenOrange = queue.poll();

            for (int[] direction : directions) {
                Pair adjacentOrange = new Pair(rottenOrange.i + direction[0],
                        rottenOrange.j + direction[1]);
                if (isValidPair(grid, adjacentOrange) && minutes[adjacentOrange.i][adjacentOrange.j] == -1) {
                    // We will only explore for Fresh Orange.
                    minutes[adjacentOrange.i][adjacentOrange.j] = minutes[rottenOrange.i][rottenOrange.j] + 1;
                    queue.add(adjacentOrange);
                }
            }
        }

        // Now just check if all oranges are rotten
        // So the maximum time in minutes will be the least amount of time required to rot all orange
        // why so, because by doing BFS only once for a visited fresh orange we make sure that the first node reaches
        // to that node will take minimum number of minutes.
        int MAX_AMONGST_MIN_MINUTES = Integer.MIN_VALUE;
        for (int i = 0; i < minutes.length; i++) {
            for (int j = 0; j < minutes[i].length; j++) {
                if (minutes[i][j] == -1) { // Means this fresh orange isn't rotted
                    return -1;
                }
                MAX_AMONGST_MIN_MINUTES = Math.max(minutes[i][j], MAX_AMONGST_MIN_MINUTES);
            }
        }
        return MAX_AMONGST_MIN_MINUTES;
    }

    private static boolean isValidPair(int[][] matrix, Pair pair) {
        int row = pair.i;
        int col = pair.j;
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[0].length;
    }

    static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
