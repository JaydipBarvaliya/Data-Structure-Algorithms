package com.leetcode.year_2020.graph.bfs.nearest_path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 *
 * @author neeraj on 01/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestPathinBinaryMatrix {

    public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{
                {0, 1},
                {1, 0}
        }));
        System.out.println(shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        }));
    }

    static int[][] directions = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0][0] != 0 || grid[grid.length - 1][grid.length - 1] != 0) return -1;
        /**
         * Same Shortest path from top left to bottom right, so let's do the BFS.
         */
        int[][] distance = new int[grid.length][grid.length];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if (grid[i][j] == 0) {
                    distance[i][j] = -1;
                }
            }
        }


        distance[0][0] = 1;
        queue.add(new Pair(0, 0)); // Top left corner.

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int[] direction : directions) {
                Pair newPair = new Pair(pair.i + direction[0],
                        pair.j + direction[1]);
                if (isValidPair(grid, newPair) && distance[newPair.i][newPair.j] == -1) {
                    // We will only explore for Fresh Orange.
                    distance[newPair.i][newPair.j] = distance[pair.i][pair.j] + 1;
                    queue.add(newPair);
                }
            }
        }
        return distance[distance.length - 1][distance.length - 1];

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
