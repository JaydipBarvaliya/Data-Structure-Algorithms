package com.leetcode.year_2020.graph.bfs.nearest_path;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * @author neeraj on 23/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ShortestPathInAGridWithObstaclesElimination {

    public static void main(String[] args) {
        System.out.println(shortestPath(new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0},
        }, 1));

        System.out.println(shortestPath(new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 0, 0}
        }, 1));
    }

    static class Pair {
        int steps; // steps taken till this point.
        int i;
        int j;
        int obstacleEliminated;

        public Pair(int i, int j, int steps, int obstacleEliminated) {
            this.i = i;
            this.j = j;
            this.steps = steps;
            this.obstacleEliminated = obstacleEliminated;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(steps, i, j, obstacleEliminated);
        }
    }

    public static int shortestPath(int[][] grid, int k) {
        /**
         * We know whenever there is a nearest/minimum steps/path problem.
         * We always need BFS. but we have a twist here, you can eliminate at most K obstacle.
         *
         * For Every BFS we need to perform following steps
         *
         * 1) Find Neighbours
         * 2) Queue : What to put in queue
         * 3) Visited Set : What to put in Visited Set
         * 4) Handle AtMost k Obstacle.
         *
         * Answers:
         * 1) We can have Right, Bottom, Left, Up
         * 2) We could have put a pair with just (i,j) but interestingly we need ObstacleCount as well here(initially it will be 0)
         *      So we will use {@link Pair}
         * 3) In Visited also we will put the same Pair object so that we can reach to the same node but with different eliminated obstacle
         *      to find the minimum number of steps. path
         * 4) So if grid[i][j] == 1 && pair.obstacleEliminated < k then we are safe to move in this grid cell else no.
         */

        Queue<Pair> queue = new LinkedList<>();
        Pair pair = new Pair(0, 0, 0, 0); // Initially we are standing on (0,0) and haven't eliminated any obstacle.
        queue.add(pair);
        Set<Pair> visited = new HashSet<>();
        visited.add(pair);

        while (!queue.isEmpty()) {
            pair = queue.poll();

            if (pair.i == grid.length - 1 && pair.j == grid[0].length - 1) {
                // We reached to the destination and since it's the BFS so shortest steps is gurantedd
                return pair.steps;
            }

            // Now let's explore all 4 directions
            // Right
            Pair newPair = getNewPair(pair, 0, 1, grid);
            if (isSafe(newPair, grid, visited, k)) {
                queue.add(newPair);
                visited.add(newPair);
            }


            // Bottom
            newPair = getNewPair(pair, 1, 0, grid);
            if (isSafe(newPair, grid, visited, k)) {
                queue.add(newPair);
                visited.add(newPair);
            }

            // Left
            newPair = getNewPair(pair, 0, -1, grid);
            if (isSafe(newPair, grid, visited, k)) {
                queue.add(newPair);
                visited.add(newPair);
            }

            // Top
            newPair = getNewPair(pair, -1, 0, grid);
            if (isSafe(newPair, grid, visited, k)) {
                queue.add(newPair);
                visited.add(newPair);
            }
        }
        return -1;
    }

    private static boolean isSafe(Pair pair, int[][] grid, Set<Pair> visited, int maxEliminationAllowed) {
        return pair != null && !visited.contains(pair) &&
                ((grid[pair.i][pair.j] == 0) ||
                        (grid[pair.i][pair.j] == 1 && pair.obstacleEliminated <= maxEliminationAllowed)
                );
    }

    private static Pair getNewPair(Pair pair, int newI, int newJ, int[][] grid) {
        if (isOutOfBounds(pair.i + newI, pair.j + newJ, grid)) return null;
        return new Pair(pair.i + newI, pair.j + newJ,
                pair.steps + 1, grid[pair.i + newI][pair.j + newJ] == 1 ?
                pair.obstacleEliminated + 1 : pair.obstacleEliminated);
    }

    private static boolean isOutOfBounds(int i, int j, int[][] grid) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }
}
