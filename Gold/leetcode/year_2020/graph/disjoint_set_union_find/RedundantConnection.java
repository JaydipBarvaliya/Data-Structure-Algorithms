package com.leetcode.year_2020.graph.disjoint_set_union_find;

import com.util.LogUtil;

/**
 * @author neeraj on 29/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RedundantConnection {

    public static void main(String[] args) {
//        LogUtil.printArray(findRedundantConnection(new int[][]{
//                {1, 2}, {1, 3}, {2, 3}
//        }));
//
//        LogUtil.printArray(findRedundantConnection(new int[][]{
//                {1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}
//        }));
//        LogUtil.printArray(findRedundantConnection(new int[][]{
//                {1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}
//        }));
        LogUtil.printArray(findRedundantConnection(new int[][]{
                {1, 5}, {3, 4}, {3, 5}, {4, 5}, {2, 4}
        }));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        /**
         * This problem can again be solved using a union find data-structure.
         */
        int[] leaders = new int[edges.length + 1];
        int[] result = new int[2];
        int[] rank = new int[edges.length + 1];
        for (int i = 0; i < leaders.length; i++) {
            leaders[i] = i; // Every one is it's own leader.
            rank[i] = 0;
        }

        for (int[] edge : edges) {
            int leaderOfNodeA = findLeader(leaders, edge[0]);
            int leaderOfNodeB = findLeader(leaders, edge[1]);
            if (leaderOfNodeA == leaderOfNodeB) {
                result = edge;
                break;
            }

            // Union by rank
            if (rank[leaderOfNodeA] >= rank[leaderOfNodeB]) {
                leaders[leaderOfNodeB] = leaderOfNodeA;

                if (rank[leaderOfNodeA] == rank[leaderOfNodeB]) {
                    rank[leaderOfNodeA] += 1;
                }
            } else {
                leaders[leaderOfNodeA] = leaderOfNodeB;
            }
        }
        return result;
    }

    private static int findLeader(int[] leaders, int i) {
        int leaderOfI = leaders[i];

        if (leaderOfI != i) { // this is path compression
            leaders[i] = findLeader(leaders, leaderOfI);
        }
        return leaders[i];
    }
}
