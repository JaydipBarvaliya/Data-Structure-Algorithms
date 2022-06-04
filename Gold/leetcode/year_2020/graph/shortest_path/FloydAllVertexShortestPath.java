package com.leetcode.year_2020.graph.shortest_path;

import com.util.LogUtil;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FloydAllVertexShortestPath {

    private static final Integer INF = 1000000;

    public static void main(String[] args) {
        printShortestPathFromEveryVertexToEveryOtherVertex(new int[][]{
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        });
    }

    public static void printShortestPathFromEveryVertexToEveryOtherVertex(int[][] distanceMatrix) {
        /**
         * graph[][] = { {0,   5,  INF, 10},
         *                     {INF,  0,  3,  INF},
         *                     {INF, INF, 0,   1},
         *                     {INF, INF, INF, 0} }
         * which represents the following graph
         *              10
         *        (0)------->(3)
         *         |         /|\
         *       5 |          |
         *         |          | 1
         *        \|/    3    |
         *        (1)------->(2)
         *   Note that the value of graph[i][j] is 0 if i is equal to j
         * And graph[i][j] is INF (infinite) if there is no edge from vertex i to j.
         *
         * Output:
         * Shortest distance matrix
         *       0      5      8      9
         *     INF      0      3      4
         *     INF    INF      0      1
         *     INF    INF    INF      0
         *
         *     So if you notice carefully originally reaching from 0 to 3 required 10 points.
         *     but as you notice now we just need 9.... if we go from 0-----> 1 ----> 2---and finally----> 3.
         */

        int[][] shortestDistance = new int[distanceMatrix.length][distanceMatrix[0].length];
        int[][] shortestPath = new int[distanceMatrix.length][distanceMatrix[0].length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                shortestDistance[i][j] = distanceMatrix[i][j]; // Initially this will be same
                // and we will update it accordingly.

                if (distanceMatrix[i][j] != INF && i != j) {
                    shortestPath[i][j] = i;
                } else {
                    shortestPath[i][j] = -1;
                }
            }
        }


        // Now coming to the algorithm, for every pair i,j
        // we want to check if there is any path from intermediate node k
        // which makes i----------> j shorter by i-------> k ---------->j

        for (int k = 0; k < distanceMatrix.length; k++) {
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (shortestDistance[i][j] > shortestDistance[i][k] + shortestDistance[k][j]) {
                        shortestDistance[i][j] = shortestDistance[i][k] + shortestDistance[k][j];
                        shortestPath[i][j] = k; // Instead of directly going from i to j
                        // we went from k.
                    }
                }
            }
        }

        LogUtil.logIt("Shortest Distance from Every Vertex to Every Other Vertex is......");
        LogUtil.printMultiDimensionArray(shortestDistance);

        LogUtil.logIt("The path followed to reach to that shortest path......");
        LogUtil.printMultiDimensionArray(shortestPath);


    }
}
