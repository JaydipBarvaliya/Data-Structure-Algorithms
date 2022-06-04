package com.leetcode.year_2020.graph.shortest_path;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NetworkDelayTime {

    private static final Integer INF = 1000000;

    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}}, 4, 2));
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        /**
         * Prepare distance Matrix
         */
        int[][] distanceMatrix = new int[N][N];
        for (int[] row : distanceMatrix) {
            Arrays.fill(row, INF);
        }
        for (int[] time : times) {
            distanceMatrix[time[0] - 1][time[1] - 1] = time[2];
        }

        int[][] shortestDistance = new int[distanceMatrix.length][distanceMatrix[0].length];
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if(i == j) continue;
                shortestDistance[i][j] = distanceMatrix[i][j]; // Initially this will be same
            }
        }
        LogUtil.printMultiDimensionArray(shortestDistance);
        // Now coming to the algorithm, for every pair i,j
        // we want to check if there is any path from intermediate node k
        // which makes i----------> j shorter by i-------> k ---------->j

        for (int k = 0; k < distanceMatrix.length; k++) {
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (shortestDistance[i][j] > shortestDistance[i][k] + shortestDistance[k][j]) {
                        shortestDistance[i][j] = shortestDistance[i][k] + shortestDistance[k][j];
                    }
                }
            }
        }

        int delayTime = Integer.MIN_VALUE;
//        LogUtil.printMultiDimensionArray(shortestDistance);
        for (int i = 0; i < N; i++) {
            if (shortestDistance[K - 1][i] == INF) return -1;
            delayTime = Math.max(delayTime, shortestDistance[K - 1][i]);
        }
        return delayTime;
    }
}
