package com.leetcode.year_2020.graph.bfs.nearest_path;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/01-matrix/
 *
 * @author neeraj on 01/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ZeroOneMatrix {
    public static void main(String[] args) {
        LogUtil.printMultiDimensionArray(updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
        LogUtil.printMultiDimensionArray(updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        }));
        LogUtil.printMultiDimensionArray(updateMatrix(new int[][]{
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1}
        }));
        LogUtil.printMultiDimensionArray(updateMatrix(new int[][]{
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1}
        }));
        LogUtil.printMultiDimensionArray(updateMatrix(new int[][]{
                {0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0}
        }));
    }

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    private static boolean isValidPair(int[][] matrix, Pair pair) {
        int row = pair.x;
        int col = pair.y;
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[0].length;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] updateMatrix(int[][] matrix) {
        int[][] distance = new int[matrix.length][matrix[0].length];
        // Since we need to find the nearest distance and it's unweighted graph we can use BFS
        // We will do BFS from all 0's and try to reach the 1 and update the distance.
        // Also note we will only perform BFS on neighbours if they haven't been touched yet.

        /**
         * Now you may ask why we are not trying all possible choices to checkout the minimum distance.
         * And the reason for that is simple in BFS the first Vertex to reach a Node is always the minimum time
         * required in an unweighted graph.
         *
         * Draw the sketch and you'll get to know why. : https://youtu.be/lyLtgxUS910?t=596
         * As in BFS the node which finds another node first is the nearest node.
         */
        // Initially all distance are -1, as we don't know the distance of anyone.
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        Queue<Pair> queue = new LinkedList<>();
        // Now we know if we are on cell 0 the minimum distance to reach 0 is 0.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new Pair(i, j)); // We are adding all 0's
                    distance[i][j] = 0;
                }
            }
        }


        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int[] direction : directions) {
                Pair newPair = new Pair(pair.x + direction[0], pair.y + direction[1]);
                if (isValidPair(matrix, newPair) && distance[newPair.x][newPair.y] == -1) { // Why check for -1, since if we have already visited this node
                    // we are not visiting it again.....
                    distance[newPair.x][newPair.y] = distance[pair.x][pair.y] + 1;
                    queue.add(newPair);
                }
            }
        }
        return distance;
    }
}
