package com.leetcode.year_2020.DP.distinct_ways_pattern;

/**
 * @author neeraj on 10/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KnightProbabilityInChessboard {

    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
        System.out.println(knightProbability(5, 2, 2, 2));
        System.out.println(knightProbability(1, 0, 0, 0));
    }

    // All 8 directions where knight can jump
    // aur India valo ke liye ghoda dhai kadam chal sakta hai kahi bhi :)
    static int[][] directions = new int[][]{
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {-1, -2}, {1, -2}
    };

    public static double knightProbability(int N, int K, int row, int col) {
        /**
         * We can do BFS starting from row and col and once all neighbours
         * of this row and col are explored...(which obviously are safe) we will consider
         * that 1 count of k is finished and we calculate total number of ways
         * in which we remain on board after making that step from all rounds of k
         *
         * and the final probability will be
         * totalSafeJumpsOfGhodaAcrossKChances = totalSafeJump / Math.pow(8, k).
         */
        return find(N, K, row, col);
    }

    public static double find(int N, int K, int r, int c) {
        if (r < 0 || c < 0 || r > N - 1 || c > N - 1) return 0;
        if (K == 0) return 1;

        double probabilityForEachRound = 1 / 8d;// For Every Kth move we get 1/8 probabilityForEachRound.
        double rate = 0;
        for (int[] direction : directions) {
            rate += probabilityForEachRound * find(N, K - 1, r + direction[0], c + direction[1]);
        }
        return rate;
    }
}
