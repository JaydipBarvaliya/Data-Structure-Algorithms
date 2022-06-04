package com.leetcode.year_2020.graph.bfs.nearest_path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 *
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestPathInABinaryMaze {

    public static void main(String[] args) {
        shortestPathBinaryMaze(new int[][]{{1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}}, new int[]{0, 0}, new int[]{3, 4});
    }

    static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static boolean isValidPair(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1;
    }

    static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int shortestPathBinaryMaze(int[][] grid, int[] source, int[] destination) {
        if (grid[source[0]][source[1]] == 0) return -1;
        /**
         * This problem is exactly similar to {@link ShortestPathinBinaryMatrix}
         * the only difference is you can start and finish as provided in the input.
         */
        int[][] distance = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    distance[i][j] = -1;
                }
            }
        }

        Pair pair = new Pair(source[0], source[1]);
        distance[source[0]][source[1]] = 0; // Initial distance from source.
        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);

        boolean hasArrivedAtDestination = false;

        while (!queue.isEmpty() && !hasArrivedAtDestination) {
            Pair currentPair = queue.poll();

            for (int[] direction : directions) {
                Pair newPair = new Pair(currentPair.i + direction[0], currentPair.j + direction[1]);

                // Why checking for distance == -1, since if it's -1 that means no one has touched it yet
                // and in BFS the first node reaching to an unvisited node will be the closest node.
                if (isValidPair(newPair.i, newPair.j, grid) && distance[newPair.i][newPair.j] == -1) {
                    distance[newPair.i][newPair.j] = distance[currentPair.i][currentPair.j] + 1;
                    if (newPair.i == destination[0] && newPair.j == destination[1]) {
                        hasArrivedAtDestination = true;
                        break;
                    }
                    queue.add(newPair);
                }
            }
        }
        System.out.println(distance[destination[0]][destination[1]]);
        return distance[destination[0]][destination[1]];
    }
}
