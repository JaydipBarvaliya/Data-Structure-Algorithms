package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MostStonesRemovedwithSameRoworColumn {

    public static void main(String[] args) {
        System.out.println(removeStones(new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        }));
        System.out.println(removeStones(new int[][]{
                {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        }));
        System.out.println(removeStones(new int[][]{
                {0, 0}
        }));

        System.out.println(removeStonesOptimal_O_N_solution(new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        }));
        System.out.println(removeStonesOptimal_O_N_solution(new int[][]{
                {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        }));
        System.out.println(removeStonesOptimal_O_N_solution(new int[][]{
                {0, 0}
        }));
    }

    public static int removeStonesOptimal_O_N_solution(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        // We assign each row and column to a unique index in the Union-Find array.
        Map<String, Integer> mapping = new HashMap<>();
        for (int[] stone : stones) {
            mapping.putIfAbsent("row:" + stone[0], mapping.size());
            mapping.putIfAbsent("col:" + stone[1], mapping.size());
        }
        int[] parents = new int[mapping.size()];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        final UnionFind unionFind = new UnionFind(mapping.size());
        for (int[] stone : stones) {
            // Let's do the union of both row and column with existing row and column, merge if required.
            unionFind.union(mapping.get("row:" + stone[0]), mapping.get("col:" + stone[1]));
        }
        return stones.length - unionFind.numComponents;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int numComponents;

        public UnionFind(int n) {
            this.numComponents = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public void union(int p, int q) {
            int leaderOfP = findParent(p);
            int leaderOfQ = findParent(q);

            if (leaderOfP == leaderOfQ) return;

            if (rank[leaderOfP] > rank[leaderOfP]) {
                parent[leaderOfQ] = leaderOfP;
            } else if (rank[leaderOfP] < rank[leaderOfP]) {
                parent[leaderOfP] = leaderOfQ;
            } else {
                // Same rank P becomes the leader of Q and increment in the rank as well
                parent[leaderOfQ] = leaderOfP;
                rank[leaderOfP]++;
            }
            numComponents--;
        }

        public int findParent(int p) {
            int parentVal = parent[p];
            if (parentVal != p) {
                parent[p] = findParent(parentVal); // path compression
            }
            return parent[p];
        }

    }

    public static int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        /**
         * We can only remove the stone if there is any other stone
         * which can cover for me (i.e other stone in the same row or column.)
         *
         *
         *
         * So essentially you have to tell whether or not there exist another stone in same row and column
         * Now the input clearly tell us in which row or column stone exist.
         *
         * every-time we have to compare ith item with jth item to check if stone exist or not.
         * [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
         *    i     j
         *               j
         *                      j
         *                            j
         *                                  j
         *  Now in next iteration
         *          i
         *              j
         *                     j
         *                          j
         *                                  j   and so on.........
         */

        int[] leaders = new int[stones.length];
        int connectedComponents = stones.length;
        for (int i = 0; i < leaders.length; i++) leaders[i] = i;

        for (int i = 0; i < stones.length - 1; i++) {
            for (int j = i + 1; j < stones.length; j++) {

                if (stones[i][0] != stones[j][0] && stones[i][1] != stones[j][1])
                    continue; // Means no other stone in the same row or column.

                // Now if they do share same row or column, let's just bind them in the same component.
                int leaderOfFirstStone = findLeader(leaders, i);
                int leaderOfSecondStone = findLeader(leaders, j);

                if (leaderOfFirstStone == leaderOfSecondStone) continue; // they already belong to the same group

                leaders[leaderOfFirstStone] = leaderOfSecondStone; // Union.
                connectedComponents--;
            }
        }
        int totalStones = stones.length;

        // We can consider all stones from connected components as just 1.
        return totalStones - connectedComponents; // is the max amount of stones we can remove.
    }

    private static int findLeader(int[] leaders, int i) {
        int leaderOfI = leaders[i];

        if (leaderOfI != i) {// Path compression
            leaders[i] = findLeader(leaders, leaderOfI);
        }
        return leaders[i];
    }
}
