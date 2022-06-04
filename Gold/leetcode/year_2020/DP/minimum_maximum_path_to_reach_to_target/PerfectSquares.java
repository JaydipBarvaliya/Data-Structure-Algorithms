package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * @author neeraj on 06/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PerfectSquares {

    static int T[];

    public static void main(String[] args) {
        System.out.println(numSquares1(12));

    }

    public static int numSquares1(int n) {
        int [] minSquares = new int[n+1];
        for(int i=0;i<=n;i++) {
            minSquares[i] = i;
        }

        for(int i=2;i<=n;i++) {
            for(int j=1;j<=i & Math.pow(j, 2)<=i;j++) {
                int perfectSquare = (int) Math.pow(j, 2);
                minSquares[i] = Math.min(minSquares[i],1+minSquares[i-perfectSquare]);
            }
        }
        return minSquares[n];
    }

    /**
     * Some Insights.....
     * We know that numSquares of when we need to achieve number -> 1
     * is 1.
     * <p>
     * So what we can do at every number is to give try of all possible numSquares below that.
     */
    public static int numSquares(int n) {
        T = new int[n + 1];
        Arrays.fill(T, -1);
        return solveNumSquares(n);
    }

    private static int solveNumSquares(int n) {
        // Base Condition
        if (n == 0) return 0;
        if (n == 1) return 1;


        if (T[n] != -1) return T[n];

        int value = Integer.MAX_VALUE;
        for (int i = 1; Math.pow(i, 2) <= n; i++) { // Trying all possible Perfect Squares
            /**
             * So if we are trying to find numSquares for 5
             * We have just 2 choices of perfect square 1 and 4, we will try both
             *
             * 1 + numSquare(5 - 1) : Taking 1 as perfect square
             * OR
             * 1 + numSquare(5 - 4) : Taking 4 as perfect square.
             */
            value = Math.min(value, 1 + solveNumSquares((int) (n - Math.pow(i, 2))));
        }
        return T[n] = value;
    }
}
