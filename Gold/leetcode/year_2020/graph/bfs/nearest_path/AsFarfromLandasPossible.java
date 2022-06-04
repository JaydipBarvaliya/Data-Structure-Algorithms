package com.leetcode.year_2020.graph.bfs.nearest_path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 *
 * @author neeraj on 01/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AsFarfromLandasPossible {

    public static void main(String[] args) {
//        System.out.println(maxDistance(new int[][]{
//                {1, 0, 1},
//                {0, 0, 0},
//                {1, 0, 1}
//        }));
        System.out.println(maxDistance(new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }));
//        System.out.println(maxDistance(new int[][]{
//                {0, 0, 1, 1, 1},
//                {0, 1, 1, 0, 0},
//                {0, 0, 1, 1, 0},
//                {1, 0, 0, 0, 0},
//                {1, 1, 0, 0, 1}
//        }));
    }

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean isValidPair(int[][] matrix, Pair pair) {
        int row = pair.x;
        int col = pair.y;
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[0].length
                && matrix[row][col] == 0;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int maxDistance(int[][] grid) {
        /**
         * This problem is as similar to {@link ZeroOneMatrix}, even if question is phrased as
         * find MaxDistance of 1 from 0....but if you observe carefully you can't be far from the nearest 1....
         * so we return MAX out of those nearest positions.
         *
         * So we find the distance from 1 and update to all zeros.
         */
        int[][] distance = new int[grid.length][grid[0].length];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                if (grid[i][j] == 0) distance[i][j] = -1;
                else {
                    // We are considering 1 here to be pushed into Queue and do the BFS from that 1.
                    distance[i][j] = 0;
                    queue.add(new Pair(i, j));
                }
            }
        }

        int MAX_DISTANCE = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int[] direction : directions) {
                Pair newPair = new Pair(pair.x + direction[0], pair.y + direction[1]);
                if (isValidPair(grid, newPair) && distance[newPair.x][newPair.y] == -1) {
                    distance[newPair.x][newPair.y] = distance[pair.x][pair.y] + 1;
                    MAX_DISTANCE = Math.max(MAX_DISTANCE, distance[newPair.x][newPair.y]);
                    queue.add(newPair);
                }
            }
        }
        return MAX_DISTANCE;
    }
}
